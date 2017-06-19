package uk.co.n3tw0rk.claymorestats.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Miners {
    private String rig;
    private String cards;
    private String version;
    private String started;
    private int uptime;
    private int hashrate;
    private int shares;
    private int rejected;
    private String pool;
    private List<Miner> miners;
}
