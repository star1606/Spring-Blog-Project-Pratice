package com.cos.springblog2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.springblog2.dto.BoardResponseDto;
import com.cos.springblog2.dto.DetailResponseDto;
import com.cos.springblog2.dto.ReplyResponseDto;
import com.cos.springblog2.model.Post;
import com.cos.springblog2.model.User;
import com.cos.springblog2.repository.CommentRepository;
import com.cos.springblog2.repository.PostRepository;
import com.cos.springblog2.repository.UserRepository;
import com.cos.springblog2.util.Script;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	// dashboard
	@GetMapping({ "/", "" })
	public String home(Model model) {
		List<Post> posts = postRepository.findAll();
		model.addAttribute("posts", posts);
		return "home";
	}

	// login
	// 페이지 이동외에 어떤 작업 후에 return을 viewResolver로 하는 것은 잘못된 방법이다.
	@GetMapping("/login")
	public String loginPage() {

		return "user/loginForm";
	}

	// loginProc로 페이지가 이동하지만 처리하는 이동이고 보여주는 페이지가 아니다.
	@PostMapping("/loginProc")
	public @ResponseBody String loginProc(User user, HttpSession session) {
		User principal = userRepository.findByUsernameAndPassword(user);

		if (principal != null) {
			session.setAttribute("principal", principal);
			return Script.href("로그인이 완료되었습니다.", "/");
		} else {
			// return Script.outText("아이디나 비밀번호가 틀렸습니다."); 이렇게만 하면 login Proc로 이동함
			return Script.href("비밀번호나 아이디가 틀렸습니다.", "/login");
			// 근데 이 부분은 script로 처리하는 것이 아닌 다른 방법으로 확인해줘야 할 것 같다.
		}

		

	}
	
	// logout
	@GetMapping("/logout") //getMapping으로 해줘야함
	public @ResponseBody String logout(HttpSession session) {
		session.invalidate();
		
		return Script.href("로그아웃하였습니다.", "/");
	}

	// join
	@GetMapping("/join")
	public String joinPage() {

		return "user/joinForm";
	}

	@PostMapping("/joinProc")
	public @ResponseBody String joinProc(User user) {
		System.out.println("joinProc 실행" + user);
		User username = userRepository.findByUsername(user.getUsername());
		
		if(username != null) {
			return Script.back("아이디가 중복되었습니다.");
			
		} else {
			userRepository.save(user);
			return Script.href("회원가입이 완료되었습니다.", "/login");
		}


		
	}

	
	// Detail Page
	@GetMapping("/detail/{id}")
	public String detailPage(@PathVariable int id, Model model) {
	BoardResponseDto boardDto = postRepository.findById(id);
		System.out.println(boardDto);
		
	List<ReplyResponseDto> replyDtos = commentRepository.findAll(id);	// postId를 받는다
	System.out.println(replyDtos);	
	
		DetailResponseDto detailDto = DetailResponseDto.builder()
				.boardDto(boardDto)
				.replyDtos(replyDtos)
				.build();
	
		System.out.println(detailDto);
		
		model.addAttribute("detailDto", detailDto);
	
//		model.addAttribute("detailDto", )
		return "board/detail";
	}
	
	// 글쓰기 페이지
	@GetMapping("/write")
	public String writePage() {
		
		return "board/write";
	}
	
	
	@PostMapping("/writeProc")
	public String write(Post post, HttpSession session){
		
		// 저장했던 세션 값을 가져옴
		User principal = (User)session.getAttribute("principal");
		
		System.out.println(principal);
		
		Post postRequest = Post.builder()
				
				.userId(principal.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.build();
		
		postRepository.save(postRequest);
		
		return "redirect:/";
	}
	
}
