package com.cos.springblog2.dto;

import java.util.List;

import com.cos.springblog2.model.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailResponseDto {

	private BoardResponseDto boardDto;
	private List<ReplyResponseDto> replyDtos;
	
	
}
