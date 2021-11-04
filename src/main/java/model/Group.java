package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private int id;
    private YearMonth period;
    private Set<LocalDateTime> plan;
    private Set<Client> clients;
}
