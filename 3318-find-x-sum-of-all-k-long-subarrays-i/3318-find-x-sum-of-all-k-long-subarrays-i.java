class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n= nums.length, j= 0;
        HashMap <Integer,Integer> map = new HashMap<>();
        int[] ans = new int[n-k+1];
        
        for(int i=0; i<k; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }
        ans[0] = getXSum(map, x);

        for(int i=k; i<n; i++){

            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
            map.put(nums[i-k], map.getOrDefault(nums[i-k],0)-1);
            if(map.get(nums[i-k])<=0) map.remove(nums[i-k]);

            j++;
            ans[j] = getXSum(map,x);
        }
        return ans;
    }

    private int getXSum(HashMap<Integer,Integer> map, int x){
        int sum = 0;

        List<int[]> list = new ArrayList<>();

        for(var entry: map.entrySet()){
            list.add(new int[]{entry.getValue(), entry.getKey()});
        }

        list.sort((a,b)->{if(a[0] != b[0]) return b[0]-a[0]; 
        return b[1]-a[1];
        });

        for(int i=0; i<Math.min(x, list.size()); i++){
            sum += list.get(i)[0] * list.get(i)[1];
        }
        return sum;
    }
}