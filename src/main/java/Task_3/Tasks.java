package Task_3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tasks {
    private String userId;
    private String id;
    private String title;
    private String completed;

}
