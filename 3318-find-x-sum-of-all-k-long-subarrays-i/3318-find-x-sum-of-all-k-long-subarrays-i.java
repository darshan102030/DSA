class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n= nums.length, j= 0;
        HashMap <Integer,Integer> map = new HashMap<>();
        int[] ans = new int[n-k+1];
        
        for(int i=0; i<k; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }

        while(k <= n){
            if(j!=0){
                map.put(nums[k-1], map.getOrDefault(nums[k-1],0)+1);
                map.put(nums[j-1], map.getOrDefault(nums[j-1],0)-1);
                if(map.get(nums[j-1]) <=0) map.remove(nums[j-1]);
            }
            List<int[]> list = new ArrayList<>();

            for(var entry: map.entrySet()){
                list.add(new int[]{entry.getValue(), entry.getKey()});
            }

            list.sort((a,b)->{if(a[0] != b[0]) return b[0]-a[0];
            return b[1]-a[1];});

            int sum=0;
            for(int i=0; i<Math.min(x, list.size()); i++){
                sum += list.get(i)[0] * list.get(i)[1];
            }
            ans[j] = sum;
            k++; j++;
        }
        return ans;
    }
}