package com.sist.manager;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

public class KeyWordManager {
	public static void main(String[] args) {
		String keyword="가성비도, 경치도, 맛도, 푸짐함도 다 있네~+_+!! 남한강쪽에 갈 일이 있어서 맛집 검색하다 찾은 가게. 평이 엄청 좋길래 기대 가득 담아 방문했다. 셀프바가 있어서 반찬을 마음껏 먹을 수 있어서 좋다. 닭갈비는 가격대비 양도 많고 살도 두툼하고 맛있다. 전체적으로 모든 게 마음에 들어서, 담에 또 방문하고 싶었다";
		KeywordExtractor ke=new KeywordExtractor();
		KeywordList list=ke.extractKeyword(keyword, true);//false = 동사까지 다
		
		for(int i=0;i<list.size();i++)
		{
			Keyword wrd=list.get(i);
			System.out.println(wrd.getString()+":"+wrd.getCnt());
		}
	}
}
