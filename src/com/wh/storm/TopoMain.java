package com.wh.storm;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class TopoMain {
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("randomspout", new RandomWordSpout(),4);
		builder.setBolt("upperbolt", new UpperBolt(),4).shuffleGrouping("randomspout");
		builder.setBolt("suffixbolt", new SuffixBolt(),4).shuffleGrouping("upperbolt");
		StormTopology topology = builder.createTopology();
		Config config = new Config();
		config.setNumWorkers(4);;
		config.setDebug(true);
		config.setNumAckers(0);
		StormSubmitter.submitTopology("demotopo", config,topology);
	}
}
