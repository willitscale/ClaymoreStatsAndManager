package uk.co.n3tw0rk.claymorestats.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Miner {
    private int temp;
    private int fan;
    private int hashrate;
}
