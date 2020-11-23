public class velocityCalculator{
  static double v(double x, double y, double z){
    double a = 30.; //Launch angle (from ground)
    double bMin = 15.; //minnimum velocity (ft/s)
    double bMax = 50.; //maximum velocity (ft/s)
    for(int i=0; i<10000; i++){ //Maximum 10000 iters
      double g   = 32.2; //gravity, feet/sec^2
      double v0  = (bMin+bMax)*.5; //approx at mean
      double tx  = Math.sqrt(x*x+y*y); //dist to goal
      double vx  = Math.cos((a*Math.PI)/180.)*v0; //x velocity
      double vt  = Math.tan((a*Math.PI)/180.); //tangent slope
      double xvx = tx/vx; //split to optimize computation time
      double ty  = -.5*g*xvx*xvx+vt*tx; //vertical hit point
      double dev = ty-z; //how far off we are
      double mx  = (vx*vx*vt)/g; //peak distance
      if(mx<tx){ //parabola peak occurs before hit
        bMin = v0;
      }else{
        if(dev/Math.abs(dev)>0.){
          bMax = v0;
        }else{
          if(Math.abs(dev)<.0000001){ //precision threshold
            return v0;
          }else{
            bMin = v0;
          }
        }
      }
    }
    return 0.; //REPORT ERROR HERE IF LINE IS REACHED
  }
  public static void main(String []args){
    double velocity = v(2.646,3.,1.5);
    System.out.println(Double.toString(velocity) + " feet/sec");
  }
}
