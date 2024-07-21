package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author raxcl
 * @date 2024-01-19 9:54:53
 */
public class QqVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3044377546418449560L;
    /**
     * qq号
     */
    private Long qq;

    /**
     * qq昵称
     */
    private String name;

    /**
     * qq邮箱
     */
    private String email;

    /**
     * qq头像
     */
    private String avatar;

    public QqVO() {
    }

    public Long getQq() {
        return this.qq;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof QqVO other)) return false;
        if (!other.canEqual(this)) return false;
        final Object this$qq = this.getQq();
        final Object other$qq = other.getQq();
        if (!Objects.equals(this$qq, other$qq)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (!Objects.equals(this$name, other$name)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (!Objects.equals(this$email, other$email)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        return Objects.equals(this$avatar, other$avatar);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof QqVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $qq = this.getQq();
        result = result * PRIME + ($qq == null ? 43 : $qq.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        return result;
    }

    public String toString() {
        return "QqVO(qq=" + this.getQq() + ", name=" + this.getName() + ", email=" + this.getEmail() + ", avatar=" + this.getAvatar() + ")";
    }
}
