package com.wh.storm;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class UpperBolt extends BaseBasicBolt {

	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String godName = tuple.getString(0);
		String godName_upper = godName.toUpperCase();
		collector.emit(new Values(godName_upper));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("uppername"));		
	}

}
