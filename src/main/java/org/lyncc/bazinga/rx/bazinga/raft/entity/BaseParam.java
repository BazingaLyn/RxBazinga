package org.lyncc.bazinga.rx.bazinga.raft.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BaseParam implements Serializable {

    public long term;

    public String serverId;

}
