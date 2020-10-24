package com.cos.springblog2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	
		// findById로 데이터를 가져오지만 SQL은 객체 저장이라는 개념이 없기 때문에
		// board가 저장이 제대로 안되고 null로 저장됨
		BoardResponseDto boardDto = postRepository.findById(id);
		System.out.println("boardDto는" +boardDto);
		System.out.println("getBoard 가져오기" + boardDto.getBoard());
		System.out.println("username 가져오기" + boardDto.getUsername());
		
		// 그래서 board 객체를 sql쿼리로 가져와서 구해야됨
		Post board = postRepository.findbyIdForPost(id);
		boardDto.setBoard(board);
		
		System.out.println("build후" +boardDto);
		
		List<ReplyResponseDto> replyDtos = commentRepository.findAll(id);	// postId를 받는다
		System.out.println(replyDtos);	
	
		DetailResponseDto detailDto = DetailResponseDto.builder()
				.boardDto(boardDto)
				.replyDtos(replyDtos)
				.build();
	
		System.out.println("deatil 페이지 detailDto" + detailDto);
		
		model.addAttribute("detailDto", detailDto);

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
	
	
	// 글삭제
	@DeleteMapping("/delete") 
	public @ResponseBody String delete(int id) {
		// s
		int result = postRepository.deleteById(id);
		System.out.println("result값" + result);
		if(result == 1) {
			return "1";
		} else {
			return "0";
		}
		
	}
	
	
	
	// 글 수정페이지, 글수정 로직
	@GetMapping("/update/{id}")
	public String updatePage(@PathVariable int id, Model model) {
		System.out.println("감??");
		BoardResponseDto boardDto = postRepository.findById(id);
		System.out.println("get Id 찾기 :" + boardDto.getBoard());
		Post board = postRepository.findbyIdForPost(id);
		boardDto.setBoard(board);
		System.out.println("update임: " + boardDto);
		model.addAttribute("boardDto", boardDto);
		return "board/update";
		
	}
	
	// 글수정 update 
	@PostMapping("/updateProc")
	public @ResponseBody String update(Post post) {
		int result = postRepository.update(post);
		if(result == 1) {
			
			return Script.href("글 수정 성공", "/");
			
		} else {
			return Script.back("글수정 실패");
		}
		
		
		
		
	}
	
	
	
}
