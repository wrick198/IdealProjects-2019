import java.util.Scanner;

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class Ali1 {
    double dismin, dismax;  //记录点到矩形的最短距离和最长距离

    double min(double a, double b) {
        return a < b ? a : b;
    }

    double max(double a, double b) {
        return a > b ? a : b;
    }

    //计算P1P2 X p1p >0
    public double GetCross(Point p1, Point p2, Point p) {
        return (p2.x - p1.x) * (p.y - p1.y) - (p.x - p1.x) * (p2.y - p1.y);
    }

    public void method() {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        String[] point = str1.split(",");
        String[] matrix = str2.split(",");

        Point p = new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1]));

        Point p1 = new Point(Double.parseDouble(matrix[0]), Double.parseDouble(matrix[1]));
        Point p2 = new Point(Double.parseDouble(matrix[2]), Double.parseDouble(matrix[3]));
        Point p3 = new Point(Double.parseDouble(matrix[4]), Double.parseDouble(matrix[5]));
        Point p4 = new Point(Double.parseDouble(matrix[6]), Double.parseDouble(matrix[7]));

        if (GetCross(p1, p, p2) * GetCross(p3, p, p4) >= 0 && GetCross(p3, p2, p) * GetCross(p4, p, p1) >= 0) {
            System.out.println("yes,0");
        } else {
            System.out.println("no,1");
        }
    }


    double dis(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    void fun(double x, double y, double x1, double y1, double x2, double y2) {
        //将对角线坐标统一成左上和右下
        double temp;
        if (x1 > x2) {
            temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y2 > y1) {
            temp = y2;
            y2 = y1;
            y1 = temp;
        }
        //9种情况分别讨论
        if (x < x1 && y > y1) {
            dismin = dis(x, y, x1, y1);
            dismax = dis(x, y, x2, y2);
            return;
        }
        if ((x <= x2 && x >= x1) && y >= y1) {
            dismin = y - y1;
            dismax = max(dis(x, y, x1, y2), dis(x, y, x2, y2));
            return;
        }
        if (x > x2 && y > y1) {
            dismin = dis(x, y, x2, y1);
            dismax = dis(x, y, x1, y2);
            return;
        }
        if (x >= x2 && (y <= y1 && y >= y2)) {
            dismin = x - x2;
            dismax = max(dis(x, y, x1, y1), dis(x, y, x1, y2));
            return;
        }
        if (x > x2 && y < y2) {
            dismin = dis(x, y, x2, y2);
            dismax = dis(x, y, x1, y1);
            return;
        }
        if ((x <= x2 && x >= x1) && y <= y2) {
            dismin = y2 - y;
            dismax = max(dis(x, y, x1, y1), dis(x, y, x2, y1));
            return;
        }
        if (x < x1 && y < y2) {
            dismin = dis(x, y, x1, y2);
            dismax = dis(x, y, x2, y1);
            return;
        }
        if (x <= x1 && (y <= y1 && y >= y2)) {
            dismin = x1 - x;
            dismax = max(dis(x, y, x2, y1), dis(x, y, x2, y2));
            return;
        }
        //点在矩形内
        dismin = min(min(x - x1, x2 - x), min(y1 - y, y - y2));
        dismax = max(max(dis(x, y, x1, y1), dis(x, y, x2, y1)), max(dis(x, y, x1, y2), dis(x, y, x2, y2)));
    }

    public static void main(String[] args) {
        Ali1 ali1 = new Ali1();
        ali1.method();
    }
}





















