package Task_2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private String userId;
    private String id;
    private String title;
    private String body;
}
