package com.glen.BlogPostSpringBoot.payload;

import java.util.List;

public class PostResponse {
	
	private List<PostDTO> content;
	private Integer pageNo;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private boolean last;
	public PostResponse() {
	
	}
	public PostResponse(List<PostDTO> content, Integer pageNo, Integer pageSize, Long totalElements, Integer totalPages,
			boolean last) {
		
		this.content = content;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}
	public List<PostDTO> getContent() {
		return content;
	}
	public void setContent(List<PostDTO> content) {
		this.content = content;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
	
}
