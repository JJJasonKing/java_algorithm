#include <numeric>
#include "LeetCodeTest.h"
#include "queue"
#include "vector"
#include <iostream>

using namespace std;
int main3() {
    int mod = 1000000007;
    int n, k;
    cin >> n >> k;
    vector<vector<int>> dp(n + 1, vector<int>(k + 1));
    dp[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
        for (int j = 0; j <= k; ++j) {
            dp[i][j] = (j > 0 ? dp[i][j - 1] : 0) + dp[i - 1][j] - (j >= i ? dp[i - 1][j - i] : 0);
            if (dp[i][j] >= mod) {
                dp[i][j] -= mod;
            } else {
                dp[i][j] += mod;
            }
        }
    }
    cout << 1232 << endl;
}


/*
5 10
1 -1 1 -1 1
100 10 100 10 100
4 4 4 4 4
 */
int main() {
    int n, k;
    cin >> n >> k;
    vector<long long> an(n);
    vector<long long> bn(n);
    vector<long long> cn(n);
    long long res = 1500;
    for (int i = 0; i < n; ++i) {
        cin >> an[i];
        res += an[i];
    }
    for (int i = 0; i < n; ++i) {
        cin >> bn[i];
        // 作弊获得的收益
        bn[i] = bn[i] - an[i];
    }
    for (int i = 0; i < n; ++i) {
        cin >> cn[i];
    }
    // 背包问题
    vector<long long> dp(k + 1);
    for (int i = 0; i < n; i++) {
        for (int j = k; j >= cn[i]; j--) {
            dp[j] = max(dp[j], dp[j - cn[i]] + bn[i]);
        }
    }
    cout << res + dp[k] << endl;
}

int mainnio1() {
    int t;
    int x, y;
    cin >> t;
    while (t-- > 0) {
        cin >> x >> y;
        if (x < y) {
            swap(x, y);
        }
        if (x % y == 0) {
            int p = x / y;
            if (p % 2 == 0) {
                cout << "B" << endl;
            } else {
                cout << "A" << endl;
            }
        } else if (x % 2 == 0 && y % 2 == 0){
            int p = x / 2;
            if (p % 2 == 0) {
                cout << "B" << endl;
            } else {
                cout << "A" << endl;
            }
        } else {
            if (x % 2 == 0) {
                cout << "B" << endl;
            } else {
                cout << "A" << endl;
            }
        }
    }
}

class Solution {
public:
    // 广度优先遍历
    int networkBecomesIdle(vector<vector<int>>& edges, vector<int>& patience) {
        int n = patience.size();
        vector<vector<int>> adj(n);
        for (auto & v : edges) {
            adj[v[0]].emplace_back(v[1]);
            adj[v[1]].emplace_back(v[0]);
        }
        int res = 0; // 表示最晚消失时间
        vector<int> vis(n);
        queue<int> q;
        vis[0] = true;
        q.push(0);
        int dis = 0;

        while (!q.empty()) {
            int len = q.size();
            while (len-- > 0) {
                int cur = q.front();
                q.pop();
                int time = 0;
                if (patience[cur] > 0) {
                    // + 前半部分表示cur最后一轮发送前消耗的时间 后半部分表示路程
                    time = (2 * dis - 1) / patience[cur] * patience[cur] + 2 * dis + 1;
                }
                res = max(res, time);
                for (int t : adj[cur]) {
                    if (!vis[t]) {
                        vis[t] = true;
                        q.push(t);
                    }
                }
            }
            dis++;
        }

        return res;
    }



    // 57 · 三数之和
    vector<vector<int>> threeSum(vector<int> &nums) {
        // write your code here
        vector<vector<int>> res;
        sort(nums.begin(), nums.end());
        int n = nums.size();
        for (int i = n - 1; i >= 0; --i) {
            if (i < n - 1 && nums[i] == nums[i+1]) continue;
            int t = nums[i];
            if (t < 0) break;
            t = -t;
            int r = i - 1, l = 0;
            while (l < r) {
                if (nums[l] + nums[r] > t) {
                    r--;
                } else if (nums[l] + nums[r] < t) {
                    l++;
                } else {
                    res.push_back({nums[l], nums[r], nums[i]});
                    l++; r--;
                    while(l < r && nums[l] == nums[l-1]) l++;
                    while(l < r && nums[r] == nums[r+1]) r--;
                }
            }
        }
        return res;
    }

    // 1132 · 合法的三角数
    // 类似 3sum 双指针
    int triangleNumber(vector<int> &nums) {
        // Write your code here
        sort(nums.begin(), nums.end());
        int n = nums.size(), res = 0;
        for (int i = n - 1; i >= 0; --i) {
            int r = i - 1, l = 0;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    // 196
    int findMissing(vector<int> &nums) {
        // 总和差：0到N的sum 减去数组元素的差值
        int sum = nums.size();
        for (int i = 0; i < nums.size(); ++i) {
            sum += (i - nums[i]);
        }
        return sum;
    }
    int findMissing2(vector<int> &nums) {
        // write your code here
        int n = nums.size();
        int i = 0;
        while (i < n) {
            while (nums[i] != i && nums[i] < n) {
                swap(nums[i], nums[nums[i]]);
            }
            i++;
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }

    int calPoints(vector<string>& ops) {
        vector<int> tmp;
        for (string t : ops) {
            if (t == "C") {
                tmp.pop_back();
            } else if (t == "D") {
                tmp.push_back(tmp.back()*2);
            } else if (t == "+") {
                int n = tmp.size();
                tmp.push_back(tmp[n-1] + tmp[n-2]);
            } else {
                tmp.push_back(stoi(t));
            }

        }
        int res = accumulate(tmp.begin() , tmp.end(), 0);
        return res;
    }

    // 436. 寻找右区间
    // 排序 + 二分
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<int> res(n, -1);
        unordered_map<int, int> um;
        for (int i = 0; i < n; ++i) {  //记录下每个区间原始位置
            um[intervals[i][0]] = i;
        }
        sort(intervals.begin(), intervals.end());
        //二分查找
        for (int i = 0; i < n; ++i) {
            int t = intervals[i][1];
            int l = i, r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (intervals[mid][0] >= t) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            if (l < n) {
                res[um[intervals[i][0]]] = um[intervals[l][0]];
            }
        }
        return res;
    }


    // 149. 直线上最多的点数
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 遍历所有点对 统计斜率
    int maxPoints(vector<vector<int>>& points) {
        int res = 1, n = points.size();
        if (n <= 2) {
            return n;
        }
        for (int i = 0; i < n; ++i) {
            unordered_map<int, int> mp;
            for (int j = i + 1; j < n; ++j) {
                int a = points[j][0] - points[i][0], b = points[j][1] - points[i][1];
                int t = gcd(a, b);
                a /= t; b /= t;
                mp[a + b * 20001]++;
            }
            for (auto &ab : mp) {
                res = max(res, ab.second + 1);
            }
        }
        return res;
    }


    // 1353. 最多可以参加的会议数目
    int maxEvents(vector<vector<int>>& events) {
        int n = events.size();
        // 按照start排序
        sort(events.begin(), events.end());
        // 小顶堆队列
        priority_queue<int, vector<int>, greater<>> pq;
        int i = 0, res = 0, day = 1;
        while (i < n || !pq.empty()) {
            // 将day天能参加的会议全部加入到优先队列，按照结束时间排序
            while (i < n && events[i][0] == day) {
                pq.push(events[i++][1]);
            }
            // 将已经结束的会议全部删掉
            while (!pq.empty() && pq.top() < day) {
                pq.pop();
            }
            // 一天只能参加一场会议将结束时间最早的安排
            if (!pq.empty()) {
                pq.pop();
                res++;
            }
            day++;
        }
        return res;
    }


    // 1235. 规划兼职工作    不限制分组数
    int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
        int n = startTime.size();
        vector<vector<int>> jobs(n, vector<int>(3));
        for (int i = 0; i < n; ++i) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        // 按照 end 排序 (begin也可以 begin的话就从后向前)
        sort(jobs.begin(), jobs.end(), [&](const auto &a, const auto &b) {
            return a[1] < b[1];
        });
        // dp[i]表示截止到当前的最大值
        vector<int> dp(n);
        for (int i = 0; i < n; ++i) {
            dp[i] = jobs[i][2];
            int l = 0, r = i - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (jobs[mid][1] <= jobs[i][0]) l = mid + 1;
                else r = mid - 1;
            }
            // 找到了
            if (r > -1) {
                dp[i] = dp[r] + jobs[i][2];
            }
            if (i > 0) {
                dp[i] = max(dp[i-1], dp[i]);
            }
        }
        return dp[n-1];
    }


    // 2054. 两个最好的不重叠活动    限制分组数==2
    int maxTwoEvents(vector<vector<int>>& events) {
        int n = events.size();
        // 按照 end 排序 (begin也可以 begin的话就从后向前)
        sort(events.begin(), events.end(), [&](const auto &a, const auto &b) {
            return a[1] < b[1];
        });
        // dp[i][k] 表示 0 ~ i 范围内, 最多选 k 个的最大价值
        // dp1和dp2 表示0~i范围内选一个和二个的最大值
        vector<int> dp1(n), dp2(n);
        for (int i = 0; i < n; ++i) {
            dp1[i] = events[i][2];
            int l = 0, r = i - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (events[mid][1] < events[i][0]) l = mid + 1;
                else r = mid - 1;
            }
            // 找到了
            if (r > -1) {
                dp2[i] = dp1[r] + events[i][2];
            }
            if (i > 0) {
                dp1[i] = max(dp1[i], dp1[i - 1]);
                dp2[i] = max(dp2[i], dp2[i - 1]);
            }
        }
        return max(dp1[n-1], dp2[n-1]);
    }


    // 1751. 最多可以参加的会议数目II    限制分组数 == k
    int maxValue(vector<vector<int>>& events, int k) {
        int n = events.size();
        // 按照 end 排序 (begin也可以 begin的话就从后向前)
        sort(events.begin(), events.end(), [&](const auto &a, const auto &b) {
            return a[1] < b[1];
        });
        // dp[i][k]：到第i的下标位置 k组的最大值
        vector<vector<int>> dp(n, vector<int>(k + 1));
        for (int i = 0; i < n; ++i) {
            int l = 0, r = i - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (events[mid][1] < events[i][0]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            for (int j = 1; j <= k; ++j) {
                dp[i][j] = events[i][2]; // init
                if (r > -1) {
                    dp[i][j] += dp[r][j-1];
                }
                if (i > 0) {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j]);
                }
            }
        }
        return dp[n - 1][k];
    }


    // 691. 贴纸拼词  记忆深搜
    int minStickers(vector<string>& stickers, string target) {
        int n = stickers.size(), m = target.size();
        // 备忘录
        unordered_map<string, int> mp;
        // stickers内每个元素的cnts
        vector<vector<int>> word_cnt(n, vector<int>(26,0));
        vector<bool> tmp(26);
        for (int i = 0; i < n; ++i) {
            for (char &c : stickers[i]) {
                word_cnt[i][c - 'a']++;
                tmp[c - 'a'] = true;
            }
        }
        for (char &c : target) {
            if (!tmp[c - 'a']) {
                return -1;
            }
        }
        mp[""] = 0; //初始化

        function<int(string)> dfs = [&](string state) {
            if (mp.count(state)) {
                return mp[state];
            }
            mp[state] = m + 1;
            vector<int> cnt(26);
            for (char &c : state) {
                cnt[c-'a']++;
            }
            // 遍历每个sticker 寻找next 求最值
            for (int i = 0; i < n; ++i) {
                // 如果当前sticker中没有state[0]这个字符则剪枝
                // 好处就是所有拼接词中都没有那么就全部continue, return
                if (word_cnt[i][state[0] - 'a'] == 0) {
                    continue;
                }
                //使用当前sticker，next为运用贴纸后剩余的字母
                string next;
                for (int j = 0; j < 26; ++j) {
                    if (cnt[j] > word_cnt[i][j]) {
                        next += string(cnt[j] - word_cnt[i][j], 'a' + j);
                    }
                }
                mp[state] = min(mp[state], dfs(next) + 1);
            }
            return mp[state];
        };

        int res = dfs(target);
        cout << res << endl;
        // 最多m个
        return res > m ? -1 : res;
    }


    // 记忆深搜 + 状态压缩
    int minStickers2(vector<string>& stickers, string target) {
        int n = target.size();
        // dp的值表示状态
        vector<int> dp(1 << n, -1);
        dp[0] = 0;
        // state 表示子串的状态
        function<int(int)> dfs = [&](int state) {
            // 备忘录遍历过 return
            if (dp[state] > -1) {
                return dp[state];
            }
            dp[state] = n + 1;
            for (string& str : stickers) {
                int child = 0; // 下一个状态
                vector<int> cnt(26);
                for (char& c : str) {
                    cnt[c-'a']++;
                }
                for (int i = 0; i < n; ++i) {
                    if (((state >> i) & 1) && cnt[target[i] - 'a'] > 0) {
                        cnt[target[i] - 'a']--;
                        child |= (1 << i);
                    }
                }
                child ^= state;
                if (child < state) {
                    dp[state] = min(dp[state], dfs(child) + 1);
                }
            }
            return dp[state];
        };

        int res = dfs((1 << n) - 1);
        return res > n ? -1 : res;
    }






};


int main22() {
    Solution s = *new Solution;
    int a, b;
    cin >> a >> b;
    // cout << "gcd:" << s.gcd(a, b);
    vector<string> ins{"bring","plane","should","against","chick"};
    string tar = "greatscore";
    s.minStickers(ins, tar);
}








class MyStack {
public:
    queue<int> q1;
    MyStack() {


    }

    void push(int x) {
        int n = q1.size();
        q1.push(x);
        // 队列翻转
        while (n-- > 0) {
            int t = q1.front();
            q1.pop();
            q1.push(t);
        }
    }

    int pop() {
        int t = q1.front();
        q1.pop();
        return t;
    }

    int top() {
        return q1.front();
    }

    bool empty() {
        return q1.empty();
    }
};



int main9(){
    int n, k = 0;
    cin >> n >> k;
    int nn = n * k;
    cout << nn << endl;
    vector<vector<int>> res(nn, vector<int>(nn));
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++) {
            int t = 0;
            cin >> t;
            for (int r = i * k; r < i * k + k; r++) {
                for (int c = j * k; c < j * k + k; c++) {
                    res[r][c] = t;
                }
            }
        }
    }
    for (int i = 0; i < nn; i++) {
        for (int j = 0; j < nn; j++) {
            cout << res[i][j] << ' ';
        }
        cout << endl;
    }

    return 0;
}


int main2(){
    string str;
    cin >> str;
    int n = str.size();
    vector<int> pre(n + 1);
    vector<int> last(n + 1);
    for (int i = 0; i < n; i++) {
        int t = str[i] == '1' ? 1 : -1;
        pre[i + 1] = pre[i] + t;
    }
    last[n] = str[n-1] == '1' ? 1 : -1;
    for (int i = n - 2; i >= 0; i--) {
        int t = str[i] == '1' ? 1 : -1;
        last[i + 1] = last[i + 2] + t;
    }

    vector<int> res;
    int len = 0;
    for (int i = n - 1; i >= 0; i--) { // len = i + 1
        for (int j = i + 1; j < n; j++) {
            if (pre[j + 1] - pre[j - i] == pre[i+1]) {
                len = i + 1;
                res.push_back(1);
                res.push_back(j+1);
                break;
            }
        }
    }
    for (int i = 0; i < n; i++) { // len = i + 1 i >= len - 1;
        for (int j = i - 1; j >= 0; j--) {
            if (last[j + 1] - last[n - i + 1] == last[i+1]) {
                len = n - i;
                res.clear();
                res.push_back(i);
                res.push_back(j+1);
                break;
            }
        }
    }

    cout << res[0] << " " << res[0] + len - 1 << " " << res[1] << " " << res[1] + len - 1 << endl;
}

