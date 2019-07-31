import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class SupportingReadMap {
	HashMap<String, ArrayList<String>> map;
	
	SupportingReadMap(String filename) throws Exception
	{
		map = new HashMap<>();
		File f = new File(filename);
		if(!f.exists())
		{
			throw new Exception("Tried to create a supporting read map from a file which does not exist");
		}
		VcfEntryIterator vei = new VcfEntryIterator(filename);
		for(VcfEntry ve : vei)
		{
			String key = ve.getKey();
			String supportingReadList = ve.getInfo("RNAMES");
			String[] nameTokens = supportingReadList.split(",");
			ArrayList<String> val = new ArrayList<String>();
			for(String s : nameTokens)
			{
				if(s.length() != 0)
				{
					val.add(s);
				}
			}
			map.put(key, val);
		}
		
	}
	
	public boolean contains(String key)
	{
		return map.containsKey(key);
	}
	
	public ArrayList<String> get(String key)
	{
		return map.get(key);
	}
	
	public String[] keyArray()
	{
		String[] res = new String[map.size()];
		int idx = 0;
		for(String s : map.keySet())
		{
			res[idx++] = s;
		}
		return res;
	}
	
}
