//case class Appliance(var power: Int)
//case class Grid(var capa:Int, appliances: Set[Int] = Set())
//
//case class World(appliance: Vector[Appliance], grid: Vector[Grid]){
//  def createGrid(power: Int): (Int, World) = {
//    val grid = Grid(power)
//    (this.grid.size, copy(grid = this.grid :+ grid))
//  }
//
//  def createAppliance(power: Int): (Int, World) = {
//    val appliance = Appliance(power)
//    (this.appliance.size, copy(appliance = this.appliance :+ appliance))
//  }
//
//  def plugInto(applianceId: Int, gridId: Int): World = {
//    val grid: Grid = this.grid(gridId)
//    val appendedGrid = grid.copy(appliances = grid.appliances + applianceId)
//    copy(grid = this.grid.filterNot(_ == grid) :+ appendedGrid)
//  }
//
//  def on(applianceId: Int): Either[Exception, World] = {
//    val appliance: Appliance = this.appliance(applianceId)
//    val gridId = grid.indexWhere(_.appliances.contains(applianceId))
//    if (0 > gridId) {
//      Left(new RuntimeException("Grid not exist"))
//    } else if (appliance.power > residualPower(gridId)) {
//      Left(new RuntimeException("No Power"))
//    } else {
//      val grid = this.grid(gridId)
//      Right(copy(grid = this.grid.updated(gridId, grid.copy(capa = grid.capa - appliance.power))))
//    }
//  }
//
//  def off(applianceId: Int): World = {
//    val appliance: Appliance = this.appliance(applianceId)
//    copy(grid = grid.map(x => {
//      if (x.appliances.contains(applianceId)) {
//        x.copy(capa = x.capa + appliance.power)
//      } else {
//        x
//      }
//    }))
//  }
//  def residualPower(gridId: Int):Int = {
//    grid(gridId).capa
//  }
//
//}
//
//object Main extends App {
//  var world = World(Vector(), Vector())
//  val (grid, world1) = world.createGrid(3000)
//  val (tv, world2) = world1.createAppliance(150)
//  val (radio, world3) = world2.createAppliance(30)
//  val world4 = world3.plugInto(tv, grid)
//  val world5 = world4.plugInto(radio, grid)
//  println(world5.residualPower(grid))
//  //val Right(world6) = world5.on(tv)
//  val Right(world6) = world5.on(tv)
//  println(world6.residualPower(grid))
//  val world7 = world6.off(tv)
//  println(world7.residualPower(grid))
//  val (monster, world8) = world7.createAppliance(100000000)
//  val world9 = world8.plugInto(monster, grid)
//  val world10 = world9.on(monster)
//
//
//  println(world)
//  println(world1)
//  println(world2)
//  println(world3)
//  println(world4)
//  println(world5)
//  println(world6)
//  println(world7)
//  println(world8)
//  println(world9)
//  println(world10)
//}
//
////val (tv, world2) = world1.createAppliance(150)
////val tv = new Appliance(150), val radio = new Appliance(30)
////val grid = new Grid(3000)
