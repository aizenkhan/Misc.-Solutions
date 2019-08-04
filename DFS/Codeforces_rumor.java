/*
  Problem at : https://codeforces.com/contest/893/problem/C
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        Sol sol = new Sol(n);
        for(int i = 1; i <= n; ++i){
            sol.addCst(i, sc.nextInt());            
        }
        while(m-- > 0)
            sol.ae(sc.nextInt(), sc.nextInt());
        sol.solve();
        System.out.println(sol.res);
    }   
}
 
class Sol {
    List<Integer>[] al;
    int[] cst;
    int[] vis;
    int n;
    long res;
    
    
    Sol(int n){
        this.n = n;
        al = new ArrayList[n+1];
        cst = new int[n+1];
        vis = new int[n+1];
        for(int i = 1; i <= n; ++i)
            al[i] = new ArrayList<Integer>();
    }
    
    void addCst(int i, int c){
        cst[i] = c;
    }
    void ae(int a, int b){
        al[a].add(b);
        al[b].add(a);
    }
    
    int run(int st, int pr){
        int min = cst[st];
        for(int nbr : al[st]){
            if(nbr == pr || vis[nbr] == 1)
                continue;
            vis[nbr] = 1;
            int chCst = run(nbr, st);
            min = min < chCst ? min : chCst;
        }
        return min;
    }
    
    void solve(){
        for(int i = 1; i <= n; ++i){
            if(vis[i] == 0){
                vis[i] = 1;
                res += run(i, 0);
            }
        }
    }
}
 
class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    }
    
    
