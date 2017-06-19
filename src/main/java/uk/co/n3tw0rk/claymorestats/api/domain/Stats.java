package uk.co.n3tw0rk.claymorestats.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stats {
    private int id;
    private String error;
    private List<String> result;
}
