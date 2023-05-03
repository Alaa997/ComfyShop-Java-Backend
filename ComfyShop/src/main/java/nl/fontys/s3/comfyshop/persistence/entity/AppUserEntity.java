//package nl.fontys.s3.comfyshop.persistence.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.Collections;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "user")
//public class AppUserEntity implements UserDetails {
//
//    @SequenceGenerator(
//            name = "user_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1
//    )
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "user_sequence"
//    )
//    private Long id;
//    @Column(name = "firstName")
//    private String firstName;
//    @Column(name = "lastName")
//    private String lastName;
//    @Column(name = "username")
//    private String username;
//    @Column(name = "address")
//    private String address;
//    @Column(name = "age")
//    private int age;
//    @Column(name = "email")
//    private String email;
//    @Column(name = "password")
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private AppUserRole appUserRole;
//    private Boolean locked;
//    private Boolean enabled;
//
//    public AppUserEntity(String firstName,
//                         String lastName,
//                         String username,
//                         String email,
//                         String address,
//                         int age,
//                         String password,
//                         AppUserRole appUserRole,
//                         Boolean locked,
//                         Boolean enabled) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.email = email;
//        this.address = address;
//        this.age = age;
//        this.appUserRole = appUserRole;
//        this.locked = locked;
//        this.enabled = enabled;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
//        return Collections.singleton(authority);
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !locked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//}
