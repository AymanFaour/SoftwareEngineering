package model;


public class SharedData {
		private User currentUser;
		private SystemUser currentSystemUser;
		private static SharedData instance = null;
	   	private SharedData() {
	   		currentUser = null;
	   		currentSystemUser = null;
	   	}

	   	public static SharedData getInstance() {
	   		if(instance == null) {
	   			instance = new SharedData();
	   		}
	   		return instance;
	   	}

		public User getCurrentUser() {
			return currentUser;
		}

		public void setCurrentUser(User currentUser) {
			this.currentUser = currentUser;
		}

		public SystemUser getCurrentSystemUser() {
			return currentSystemUser;
		}

		public void setCurrentSystemUser(SystemUser currentSystemUser) {
			this.currentSystemUser = currentSystemUser;
		}
	   	
}