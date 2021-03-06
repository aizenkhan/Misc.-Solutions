/*
  Problem at : https://codeforces.com/contest/923/problem/B
*/

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        Scan sc = new Scan();
        int n = sc.scanInt();
        Sol sol = new Sol(n);
        for(int i = 1; i <= n; ++i)
            sol.addV(i, sc.scanInt());
            
        for(int i = 1; i <= n; ++i)
            sol.addT(i, sc.scanInt());
            
        sol.solve();
    }
}

class Sol {
    long[] V,T,cumS;
    ArrayList<Integer>[] last;
    int n;
    
    Sol(int n){
        this.n = n;
        V = new long[n+1];
        T = new long[n+1];
        cumS = new long[n+1];
        last = new ArrayList[n+1];
    }
    
    void addV(int i, int val){
        V[i] = (long)val;
    }
    
    void addT(int i, int val){
        T[i] = (long) val;
        cumS[i] = (cumS[i-1]+val);
    }
    
    void fillcumS(){
        for(int i = 1; i <= n; ++i){
            int pos = bS(i, n+1, V[i]+cumS[i-1]);
            if(pos <= n){
                if(last[pos] == null)
                    last[pos] = new ArrayList<Integer>();
                last[pos].add(i);
            }
        }        
    }
    
    int bS(int l, int h, long key){
        while(l < h){
            int mid = (l+h)/2;
            if(cumS[mid] >= key)
                h = mid;
            else
                l = mid + 1;
        }
        return l;
    }
    
    void solve(){
        fillcumS();
        long curr = 0;
        for(int i = 1; i <= n; ++i){
            ++curr;
            long res = curr*T[i];
            if(last[i] != null){
                for(int j : last[i]){
                    res -= (cumS[i] - V[j] - cumS[j-1]);
                    curr-=1;
                }
            }
           System.out.print(res + " ");
        }
    }
}

class Scan
{
    private byte[] buf=new byte[1024];
    private int index;
    private InputStream in;
    private int total;
    public Scan()
    {
        in=System.in;
    }
    public int scan()throws IOException
    {
        if(total<0)
        throw new InputMismatchException();
        if(index>=total)
        {
            index=0;
            total=in.read(buf);
            if(total<=0)
            return -1;
        }
        return buf[index++];
    }
    public int scanInt()throws IOException
    {
        int integer=0;
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        int neg=1;
        if(n=='-')
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n))
        {
            if(n>='0'&&n<='9')
            {
                integer*=10;
                integer+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*integer;
    }
    public double scanDouble()throws IOException
    {
        double doub=0;
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        int neg=1;
        if(n=='-')
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n)&&n!='.')
        {
            if(n>='0'&&n<='9')
            {
                doub*=10;
                doub+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        if(n=='.')
        {
            n=scan();
            double temp=1;
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    temp/=10;
                    doub+=(n-'0')*temp;
                    n=scan();
                }
                else throw new InputMismatchException();
            }
        }
        return doub*neg;
    }
    public String scanString()throws IOException
    {
        StringBuilder sb=new StringBuilder();
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        while(!isWhiteSpace(n))
        {
            sb.append((char)n);
            n=scan();
        }
        return sb.toString();
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}

