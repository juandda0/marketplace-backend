/**
 * Entity Token: Represents a token used for authentication or authorization.
 * <p>
 * Tokens are issued to users and can be used to authenticate or authorize access to resources.
 * Tokens can be revoked or expired, and the type of token (e.g. Bearer) is stored.
 */
package com.juannn.Login_register.model.user;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tokens")
public class Token {

    /**
     * Enum TokenType: Represents the type of token.
     * <p>
     * Currently only Bearer tokens are supported.
     */
    public enum TokenType{
        BEARER
    }

    /**
     * Id of the token.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * Unique token string.
     */
    @Column(unique = true)
    public String token;

    /**
     * Type of token.
     */
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    /**
     * If the token is revoked.
     */
    public boolean revoked;

    /**
     * If the token is expired.
     */
    public boolean expired;

    /**
     * The user associated with the token.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}