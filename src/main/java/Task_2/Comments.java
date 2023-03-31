package Task_2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments {
    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;
}
