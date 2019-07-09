package org.lyncc.bazinga.rx.bazinga.raft;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NodeConfig {

    public int selfPort;

    private List<String> peerAdds;
}
