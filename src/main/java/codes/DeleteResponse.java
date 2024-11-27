package codes;

public class DeleteResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDeletedNews() {
        return deletedNews;
    }

    public void setDeletedNews(Object deletedNews) {
        this.deletedNews = deletedNews;
    }

    String message;
    Object deletedNews;
}
