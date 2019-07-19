package com.wh.storm;

import java.util.Map;
import java.util.Random;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RandomWordSpout  extends BaseRichSpout{
	private SpoutOutputCollector collector;
	private String[] word = new String[]{"huawei","oppo","vivo","meizu","xiaomi","iphone","zhongxing","chuizi"};
	@Override
	public void nextTuple() {
		Random random = new Random();
		int index = random.nextInt(word.length);
		String godName = word[index];	
		collector.emit(new Values(godName));
		Utils.sleep(500);
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		this.collector=arg2;	
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("orginname"));		
	}

}
