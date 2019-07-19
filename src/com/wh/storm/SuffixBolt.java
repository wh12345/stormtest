package com.wh.storm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class SuffixBolt extends BaseBasicBolt{
	
	private FileWriter fileWriter;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		try {
			fileWriter = new FileWriter("/home/hadoop/stormoutput/"+UUID.randomUUID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String upper_name = tuple.getString(0);
		String suffix_name = upper_name +"tt";
		try {
			fileWriter.write(suffix_name);
			fileWriter.write("\n");
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {			
	}

}
