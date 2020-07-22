package board.model;

public class Board {
	private int idx;
	private String userId;
	private String title;
	private String itemCategory;
	private String content;
	private String regDate;
	private String viewCount;
	private String fileContentAddr;
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Board() {}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	public String getFileContentAddr() {
		return fileContentAddr;
	}
	public void setFileContentAddr(String fileContentAddr) {
		this.fileContentAddr = fileContentAddr;
	}
	@Override
	public String toString() {
		return "Board [idx=" + idx + ", userId=" + userId + ", title=" + title + ", itemCategory=" + itemCategory
				+ ", content=" + content + ", regDate=" + regDate + ", viewCount=" + viewCount + ", fileContentAddr="
				+ fileContentAddr + "]";
	}
	public Board(int idx, String userId, String title, String itemCategory, String content, String viewCount,
			String fileContentAddr, String regDate) {
		super();
		this.idx = idx;
		this.userId = userId;
		this.title = title;
		this.itemCategory = itemCategory;
		this.content = content;
		this.regDate = regDate;
		this.viewCount = viewCount;
		this.fileContentAddr = fileContentAddr;
	}
	
}

