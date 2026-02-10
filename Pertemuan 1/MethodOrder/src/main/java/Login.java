public class Login {
    boolean status = false;
    boolean crud = false;

    public void login(Boolean status) {
        this.status = status;
    }

    public void CRUD(Boolean status) {
        this.crud = status;
    }

    public boolean isLogin() {
        return status;
    }

    public boolean isCrudAllowed() {
        return crud;
    }
}
