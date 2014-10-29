/**
 * 
 */
package cn.AudioChat.framework.dao;

/**
 * @author Changwy
 * 
 */
public class PageQuery {
	public static int DEFAULT_PAGE_SIZE = 15;
	private int startIndex;
	private int pageSize;

	public PageQuery(int startIndex, int pageSize) {
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
