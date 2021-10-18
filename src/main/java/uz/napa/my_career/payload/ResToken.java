package uz.napa.my_career.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResToken {
    private String type="Bearer ";
    private String token;

    public ResToken(String token) {
        this.token = token;
    }
    
}
