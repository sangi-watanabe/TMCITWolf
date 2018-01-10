package tmcit;

import java.util.ArrayList;
import java.util.List;

import org.aiwolf.client.lib.Content;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Talk;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.sample.lib.AbstractVillager;

public class MouseVillager extends AbstractVillager {

	Agent me;
	GameInfo currentGameInfo;
	List<Agent> aliveList = new ArrayList<>();

	@Override
	public void dayStart() {

	}

	@Override
	public void finish() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String getName() {
		return "MouseVillager";
	}

	@Override
	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		me=gameInfo.getAgent();
	}

	@Override
	public String talk() {
		return Content.OVER.getText();
	}

	@Override
	public void update(GameInfo gameInfo) {
		currentGameInfo=gameInfo;
		List<Talk> talkList = currentGameInfo.getTalkList();
		String hoge = "";
		int i;
		System.out.println("hoeghoeg" + talkList.size());
		for(i=0; i < talkList.size(); i++) {
			hoge = talkList.get(i).getText();
			System.out.println(hoge);
		}
	}

	public Agent vote() {
		//もし、whisperで吊る対象を決定した場合にはその対象を選択
		//最も村人らしい人物を優先的に襲う。
		aliveList = new ArrayList<>(currentGameInfo.getAliveAgentList());
		aliveList.remove(me);
		return randomSelect(aliveList);
	}
	<T> T randomSelect(List<T> list ) {
		if ( list.isEmpty()) {
			return null;
		}
		else {
			return list.get((int) (Math.random() * list.size()));
		}
	}
}
