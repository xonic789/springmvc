package hello.springmvc.basic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Data
public class HelloData {

    private String username;
    private int age;
}
