package ch03

import cats.data.State

case class Appliance(power: Int, isOn: Boolean, grid: Option[Int])
case class Grid(capa: Int)
case class World(appliance: Vector[Appliance], grid: Vector[Grid])

object Main extends App {
  def createGrid(power: Int): State[World, Int] = State { world =>
    val grid = Grid(power)
    (world.copy(grid = world.grid :+ grid), world.grid.size)
  }

  def createAppliance(power: Int): State[World, Int] = State { world =>
    val appliance = Appliance(power, isOn = false, None)
    (world.copy(appliance = world.appliance :+ appliance), world.appliance.size)
  }

  def plugInto(applianceId: Int, gridId: Int): State[World, Unit] = State { world =>
    val app = world.appliance(applianceId)
    (world.copy(world.appliance.updated(applianceId, app.copy(grid = Some(gridId)))), ())
  }

  def on(applianceId: Int): State[World, Either[Exception, Unit]] = State { world =>
    val app: Appliance = world.appliance(applianceId)
    app.grid match {
      case None =>
        (world, Left(new RuntimeException("Grid not exist")))
      case Some(gridId) if app.power > residualPower(world, gridId) =>
        (world, Left(new RuntimeException("No Power")))
      case Some(_) =>
        (world.copy(world.appliance.updated(applianceId, app.copy(isOn = true))), Right(()))
    }
  }

  def off(applianceId: Int): State[World, Unit] = State { world =>
    val app = world.appliance(applianceId)
    (world.copy(world.appliance.updated(applianceId, app.copy(isOn = false))), ())
  }

  def residualPower(world: World, gridId: Int): Int = {
    val grid = world.grid(gridId)
    grid.capa - world.appliance.filter(_.grid.contains(gridId)).map(_.power).sum
  }

  val program = for {
    grid <- createGrid((3000))
    tv <- createAppliance(150)
    radio <- createAppliance(30)
    _ <- plugInto(tv, grid)
    _ <- plugInto(radio, grid)
    _ <- on(tv)
  } yield println(grid, tv, radio)

  println((program run World(Vector(), Vector())).value)

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
}

//val (tv, world2) = world1.createAppliance(150)
//val tv = new Appliance(150), val radio = new Appliance(30)
//val grid = new Grid(3000)

