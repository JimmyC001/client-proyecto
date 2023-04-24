package co.com.cattleya.auth.domain.model;

public enum UserRole {
    CLIENT("CLIENT"), PROVIDER("PROVIDER"), ADMIN("ADMIN");
    private final String name;
    UserRole(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static UserRole getFromString(String name){
        for(UserRole role: UserRole.values()){
            if(name.equals(role.name)){
                return role;
            }
        }
        throw new IllegalArgumentException("There is no a Role with value " + name);
    }
}
