package cn.crabapples;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class SysUser implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private static final long serialVersionUID = 1L;
}