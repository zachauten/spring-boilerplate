package boilerplate.model.requests;

import java.util.Optional;

public record PersonUpdateRequest(
    Optional<String> name,
    Optional<Integer> age
    
) {
    
}
