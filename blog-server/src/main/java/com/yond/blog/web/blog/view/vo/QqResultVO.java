package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author raxcl
 * @date 2024-01-19 9:54:53
 */
public class QqResultVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4454455192459503846L;
    private String success;

    private String msg;

    private Map<String, Object> data;

    private String time;

    private String api_vers;

    public QqResultVO() {
    }

    public String getSuccess() {
        return this.success;
    }

    public String getMsg() {
        return this.msg;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String getTime() {
        return this.time;
    }

    public String getApi_vers() {
        return this.api_vers;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setApi_vers(String api_vers) {
        this.api_vers = api_vers;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof QqResultVO other)) return false;
        if (!other.canEqual(this)) return false;
        final Object this$success = this.getSuccess();
        final Object other$success = other.getSuccess();
        if (!Objects.equals(this$success, other$success)) return false;
        final Object this$msg = this.getMsg();
        final Object other$msg = other.getMsg();
        if (!Objects.equals(this$msg, other$msg)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (!Objects.equals(this$data, other$data)) return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (!Objects.equals(this$time, other$time)) return false;
        final Object this$api_vers = this.getApi_vers();
        final Object other$api_vers = other.getApi_vers();
        return Objects.equals(this$api_vers, other$api_vers);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof QqResultVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $success = this.getSuccess();
        result = result * PRIME + ($success == null ? 43 : $success.hashCode());
        final Object $msg = this.getMsg();
        result = result * PRIME + ($msg == null ? 43 : $msg.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        final Object $api_vers = this.getApi_vers();
        result = result * PRIME + ($api_vers == null ? 43 : $api_vers.hashCode());
        return result;
    }

    public String toString() {
        return "QqResultVO(success=" + this.getSuccess() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ", time=" + this.getTime() + ", api_vers=" + this.getApi_vers() + ")";
    }
}
