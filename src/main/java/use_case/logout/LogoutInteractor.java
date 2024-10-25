package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {

    private final LogoutUserDataAccessInterface userDataAccessObject;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        final String username = logoutInputData.getUsername();
        final String currentUsername = userDataAccessObject.getCurrentUsername();

        if (currentUsername != null && currentUsername.equals(username)) {
            userDataAccessObject.logout();
            final LogoutOutputData outputData = new LogoutOutputData(username, false);
            logoutPresenter.prepareSuccessView(outputData);
        }
        else {
            logoutPresenter.prepareFailView("Logout failed: User not logged in or username mismatch.");
        }
    }

}
