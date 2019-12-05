package com.upsoft.environmentmonitoring.domain.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName PageListVO
 * @Description TODO
 * @Author wei wei
 * @Date 2018/12/12 9:49
 * @Version 1.0
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageListVO<T> implements Serializable {

	/**
	 * @Description: 总条数
	 */
	private Integer total;

	/**
	 * @Description: 总页数
	 */
	private Integer totalPage;

	/**
	 * @Description: 当前页码
	 */
	private Integer pageNumber;

	/**
	 * @Description: 分页大小
	 */
	private Integer pageSize;

	/**
	 * @Description: 当前页码数据内容
	 */
	private T data;

}
