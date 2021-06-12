package by.resliv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "keyresponse")
public class KeyResponse {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @SequenceGenerator(name = "keyResponseId", sequenceName = "keyresponse_keys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keyResponseId")
    private long id;

    @NotBlank
    @NotEmpty
    @Column(name = "key", updatable = true, nullable = false)
    private String key;

    @NotBlank
    @NotEmpty
    @Column(name = "response", updatable = true, nullable = false)
    private String response;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyResponse that = (KeyResponse) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
