package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.valueobject.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void createAccount_ShouldSucceed_WhenValidData() {
        Name name = new Name("Nguyen Van A");
        Email email = new Email("test@example.com");
        Password password = new Password("password123");

        Account account = Account.create(name, email, password);

        assertNotNull(account.getId());
        assertEquals(name, account.getName());
        assertEquals(email, account.getEmail());
        assertEquals(password, account.getPassword());
        assertFalse(account.isActive());
    }

    @Test
    void deleteAccount_ShouldBeInactive() {
        Account account = Account.create(new Name("A"), new Email("a@b.com"), new Password("p"));
        account.activate();
        assertTrue(account.isActive());

        account.delete();

        assertFalse(account.isActive());
    }

    @Test
    void entityEquality_ShouldBasedOnId() {
        UUID id = UUID.randomUUID();
        Name name1 = new Name("Name 1");
        Name name2 = new Name("Name 2");
        
        // This requires a way to create account with specific ID for testing, 
        // but since we use Account.create with random UUID, we can test Role instead
        Role role1 = new Role(id, new RoleName("ADMIN"), new RoleLevel(1));
        Role role2 = new Role(id, new RoleName("USER"), new RoleLevel(2));
        
        assertEquals(role1, role2);
        assertEquals(role1.hashCode(), role2.hashCode());
    }
}
