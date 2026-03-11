public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(String id) {
        User user = userRepository.findById(id);
        return user != null ? user.name : null;
    }

    public void register(User user) {
        userRepository.save(user);
    }
}
