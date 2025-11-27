package uk.linyi.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Nested
    @DisplayName("用户注册测试")
    class RegistrationTests {

        @Test
        @DisplayName("正常注册应该成功")
        void testSuccessfulRegistration() {
            boolean result = user.register("testuser", "password123", "test@example.com");

            assertTrue(result);
            assertEquals("testuser", user.getUsername());
            assertEquals("password123", user.getPassword());
            assertEquals("test@example.com", user.getEmail());
            assertFalse(user.isLoggedIn());
            assertTrue(user.isRegistered());
            assertTrue(user.canLogin());
        }

        @Test
        @DisplayName("空用户名注册应该失败")
        void testRegistrationWithEmptyUsername() {
            boolean result = user.register("", "password123", "test@example.com");

            assertFalse(result);
            assertFalse(user.isRegistered());
        }

        @Test
        @DisplayName("null用户名注册应该失败")
        void testRegistrationWithNullUsername() {
            boolean result = user.register(null, "password123", "test@example.com");

            assertFalse(result);
            assertFalse(user.isRegistered());
        }

        @Test
        @DisplayName("空密码注册应该失败")
        void testRegistrationWithEmptyPassword() {
            boolean result = user.register("testuser", "", "test@example.com");

            assertFalse(result);
            assertFalse(user.isRegistered());
        }

        @Test
        @DisplayName("null密码注册应该失败")
        void testRegistrationWithNullPassword() {
            boolean result = user.register("testuser", null, "test@example.com");

            assertFalse(result);
            assertFalse(user.isRegistered());
        }

        @Test
        @DisplayName("空邮箱注册应该失败")
        void testRegistrationWithEmptyEmail() {
            boolean result = user.register("testuser", "password123", "");

            assertFalse(result);
            assertFalse(user.isRegistered());
        }

        @Test
        @DisplayName("null邮箱注册应该失败")
        void testRegistrationWithNullEmail() {
            boolean result = user.register("testuser", "password123", null);

            assertFalse(result);
            assertFalse(user.isRegistered());
        }

        @Test
        @DisplayName("只包含空格的用户名注册应该失败")
        void testRegistrationWithWhitespaceOnlyUsername() {
            boolean result = user.register("   ", "password123", "test@example.com");

            assertFalse(result);
            assertFalse(user.isRegistered());
        }
    }

    @Nested
    @DisplayName("用户登录测试")
    class LoginTests {

        @BeforeEach
        void setUpLoginTests() {
            user.register("testuser", "password123", "test@example.com");
        }

        @Test
        @DisplayName("正确凭据登录应该成功")
        void testSuccessfulLogin() {
            boolean result = user.login("testuser", "password123");

            assertTrue(result);
            assertTrue(user.isLoggedIn());
            assertFalse(user.canLogin());
        }

        @Test
        @DisplayName("错误用户名登录应该失败")
        void testLoginWithWrongUsername() {
            boolean result = user.login("wronguser", "password123");

            assertFalse(result);
            assertFalse(user.isLoggedIn());
            assertTrue(user.canLogin());
        }

        @Test
        @DisplayName("错误密码登录应该失败")
        void testLoginWithWrongPassword() {
            boolean result = user.login("testuser", "wrongpassword");

            assertFalse(result);
            assertFalse(user.isLoggedIn());
            assertTrue(user.canLogin());
        }

        @Test
        @DisplayName("未注册用户登录应该失败")
        void testLoginWithUnregisteredUser() {
            User unregisteredUser = new User();
            boolean result = unregisteredUser.login("testuser", "password123");

            assertFalse(result);
            assertFalse(unregisteredUser.isLoggedIn());
        }

        @Test
        @DisplayName("已登录用户重复登录应该成功")
        void testLoginWhenAlreadyLoggedIn() {
            user.login("testuser", "password123");
            boolean result = user.login("testuser", "password123");

            assertTrue(result);
            assertTrue(user.isLoggedIn());
        }
    }

    @Nested
    @DisplayName("用户登出测试")
    class LogoutTests {

        @Test
        @DisplayName("已登录用户登出应该成功")
        void testLogoutAfterLogin() {
            user.register("testuser", "password123", "test@example.com");
            user.login("testuser", "password123");

            user.logout();

            assertFalse(user.isLoggedIn());
            assertTrue(user.canLogin());
            assertTrue(user.isRegistered());
        }

        @Test
        @DisplayName("未登录用户登出应该成功")
        void testLogoutWithoutLogin() {
            user.register("testuser", "password123", "test@example.com");

            user.logout();

            assertFalse(user.isLoggedIn());
            assertTrue(user.canLogin());
        }
    }

    @Nested
    @DisplayName("用户状态检查测试")
    class StatusCheckTests {

        @Test
        @DisplayName("新用户状态检查")
        void testNewUserStatus() {
            assertFalse(user.isRegistered());
            assertFalse(user.isLoggedIn());
            assertFalse(user.canLogin());
        }

        @Test
        @DisplayName("已注册但未登录用户状态检查")
        void testRegisteredUserStatus() {
            user.register("testuser", "password123", "test@example.com");

            assertTrue(user.isRegistered());
            assertFalse(user.isLoggedIn());
            assertTrue(user.canLogin());
        }

        @Test
        @DisplayName("已登录用户状态检查")
        void testLoggedInUserStatus() {
            user.register("testuser", "password123", "test@example.com");
            user.login("testuser", "password123");

            assertTrue(user.isRegistered());
            assertTrue(user.isLoggedIn());
            assertFalse(user.canLogin());
        }
    }

    @Nested
    @DisplayName("继承和角色测试")
    class InheritanceAndRoleTests {

        @Test
        @DisplayName("用户角色应该是USER")
        void testUserRole() {
            assertEquals("USER", user.getRole());
        }

        @Test
        @DisplayName("构造函数测试")
        void testConstructors() {
            User userWithId = new User(1L, "Test User", "test@example.com", 25, "testuser", "password123");

            assertEquals(1L, userWithId.getId());
            assertEquals("Test User", userWithId.getName());
            assertEquals("test@example.com", userWithId.getEmail());
            assertEquals(25, userWithId.getAge());
            assertEquals("testuser", userWithId.getUsername());
            assertEquals("password123", userWithId.getPassword());
            assertEquals("USER", userWithId.getRole());
        }

        @Test
        @DisplayName("Lombok注解生成的getter/setter测试")
        void testLombokGeneratedMethods() {
            user.setUsername("newuser");
            user.setPassword("newpass");
            user.setLoggedIn(true);

            assertEquals("newuser", user.getUsername());
            assertEquals("newpass", user.getPassword());
            assertTrue(user.isLoggedIn());
        }
    }

    @Nested
    @DisplayName("边界条件测试")
    class EdgeCaseTests {

        @Test
        @DisplayName("用户名包含空格应该允许")
        void testUsernameWithSpaces() {
            boolean result = user.register("test user", "password123", "test@example.com");

            assertTrue(result);
            assertEquals("test user", user.getUsername());
        }

        @Test
        @DisplayName("密码包含空格应该允许")
        void testPasswordWithSpaces() {
            boolean result = user.register("testuser", "pass word 123", "test@example.com");

            assertTrue(result);
            assertEquals("pass word 123", user.getPassword());
        }

        @Test
        @DisplayName("邮箱格式验证不应该阻止注册")
        void testEmailFormatValidation() {
            boolean result = user.register("testuser", "password123", "invalid-email");

            assertTrue(result);
            assertEquals("invalid-email", user.getEmail());
        }
    }
}