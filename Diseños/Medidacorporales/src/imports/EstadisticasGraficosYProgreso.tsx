import svgPaths from "./svg-z9tioa82qv";
import { Activity, Calendar, Flame, TrendingUp, Clock, Dumbbell } from "lucide-react";
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from "recharts";

function Shape() {
  return (
    <div className="absolute left-1/2 size-[24px] top-1/2 translate-x-[-50%] translate-y-[-50%]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 24 24">
        <g id="drawer">
          <path d={svgPaths.p21dbd80} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" />
        </g>
      </svg>
    </div>
  );
}

function Frame5() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(241,241,243,0.5)] relative rounded-[40px] shadow-[0px_0px_65px_0px_rgba(0,0,0,0.25)] shrink-0 size-[50px]">
      <Shape />
    </div>
  );
}

function Frame6() {
  return (
    <div className="basis-0 grow h-full min-h-px min-w-px relative shrink-0">
      <div className="flex flex-col justify-center size-full">
        <div className="box-border content-stretch flex flex-col items-start justify-center px-[8px] py-[4px] relative size-full">
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] font-bold leading-[normal] relative shrink-0 text-[20px] text-black w-full">Estadísticas</p>
        </div>
      </div>
    </div>
  );
}

function Left() {
  return (
    <div className="basis-0 content-stretch flex gap-[10px] grow items-center min-h-px min-w-px relative shrink-0" data-name="Left">
      <Frame5 />
      <div className="basis-0 flex flex-row grow items-center self-stretch shrink-0">
        <Frame6 />
      </div>
    </div>
  );
}

function Shape1() {
  return (
    <div className="absolute left-1/2 size-[24px] top-1/2 translate-x-[-50%] translate-y-[-50%]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 24 24">
        <g id="search">
          <path d={svgPaths.p17fb4ac0} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" strokeWidth="0.333333" />
        </g>
      </svg>
    </div>
  );
}

function Frame7() {
  return (
    <div className="relative rounded-[40px] shrink-0 size-[50px]">
      <Shape1 />
    </div>
  );
}

function Shape2() {
  return (
    <div className="absolute left-1/2 size-[24px] top-1/2 translate-x-[-50%] translate-y-[-50%]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 24 24">
        <g id="more">
          <path d={svgPaths.p34a001c0} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" strokeWidth="0.333333" />
        </g>
      </svg>
    </div>
  );
}

function Frame8() {
  return (
    <div className="relative rounded-[40px] shrink-0 size-[50px]">
      <Shape2 />
    </div>
  );
}

function Right() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(241,241,243,0.5)] box-border content-stretch flex items-center justify-end relative rounded-[25px] shadow-[0px_0px_65px_0px_rgba(0,0,0,0.25)] shrink-0" data-name="Right">
      <Frame7 />
      <Frame8 />
    </div>
  );
}

function TopAppBar() {
  return (
    <div className="absolute box-border content-stretch flex items-center justify-between left-0 px-[18px] py-[12px] top-0 w-[440px]" data-name="Top app bar">
      <div className="absolute flex h-[54px] items-center justify-center left-[calc(50%-0.5px)] top-0 translate-x-[-50%] w-[553px]">
        <div className="flex-none scale-y-[-100%]">
          <div className="bg-gradient-to-b from-[rgba(241,241,243,0)] h-[54px] to-[#f1f1f3] w-[553px]" />
        </div>
      </div>
      <Left />
      <Right />
    </div>
  );
}

function Home() {
  return (
    <div className="absolute inset-0 overflow-clip" data-name="home">
      <div className="absolute aspect-[22/24] bottom-[16.67%] left-[calc(50%-0.21px)] top-[12.5%] translate-x-[-50%]" data-name="Vector">
        <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 13 15">
          <g id="Vector">
            <path clipRule="evenodd" d={svgPaths.p3786d100} fill="var(--fill-0, white)" fillRule="evenodd" />
            <path d={svgPaths.p34240080} stroke="var(--stroke-0, black)" strokeOpacity="0.6" />
          </g>
        </svg>
      </div>
    </div>
  );
}

function Shape3() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <Home />
    </div>
  );
}

function Frame() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape3 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Inicio</p>
    </div>
  );
}

function FloatingNavBarItem() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0" data-name="Floating nav bar item">
      <IconWithLabel />
    </div>
  );
}

function Shape4() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="report">
          <path d={svgPaths.p2bb5ab80} id="Vector" stroke="var(--stroke-0, black)" strokeLinecap="round" strokeLinejoin="round" strokeWidth="1.5" />
        </g>
      </svg>
    </div>
  );
}

function Frame1() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape4 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel1() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame1 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-black text-center w-[84px]">Estadisticas</p>
    </div>
  );
}

function FloatingNavBarItem1() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel1 />
    </div>
  );
}

function Shape5() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="workout">
          <path clipRule="evenodd" d={svgPaths.p2dbf8600} fill="var(--fill-0, black)" fillOpacity="0.6" fillRule="evenodd" id="Vector" />
        </g>
      </svg>
    </div>
  );
}

function Frame2() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape5 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel2() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame2 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Entrenos</p>
    </div>
  );
}

function FloatingNavBarItem2() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel2 />
    </div>
  );
}

function Shape6() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="height">
          <path clipRule="evenodd" d={svgPaths.p1c1e7c80} fill="var(--fill-0, black)" fillOpacity="0.6" fillRule="evenodd" id="Vector" />
          <path d={svgPaths.p38635680} id="Vector_2" stroke="var(--stroke-0, black)" strokeLinecap="round" strokeOpacity="0.6" strokeWidth="1.5" />
        </g>
      </svg>
    </div>
  );
}

function Frame3() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape6 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel3() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame3 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Medidas</p>
    </div>
  );
}

function FloatingNavBarItem3() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel3 />
    </div>
  );
}

function Shape7() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="drawer">
          <g id="Vector">
            <path d={svgPaths.p9727780} fill="var(--fill-0, white)" />
            <path d={svgPaths.p20e14080} stroke="var(--stroke-0, black)" strokeOpacity="0.6" />
          </g>
        </g>
      </svg>
    </div>
  );
}

function Frame4() {
  return (
    <div className="relative rounded-[48px] shrink-0 w-full">
      <div className="flex flex-row items-center justify-center overflow-clip rounded-[inherit] size-full">
        <div className="box-border content-stretch flex gap-[10px] items-center justify-center px-[8px] py-[2px] relative w-full">
          <Shape7 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel4() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame4 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Menu</p>
    </div>
  );
}

function FloatingNavBarItem4() {
  return (
    <div className="box-border content-stretch flex gap-[10px] items-center justify-center overflow-clip px-[12px] py-[4px] relative rounded-[79px] shrink-0 w-[78px]" data-name="Floating nav bar item">
      <IconWithLabel4 />
    </div>
  );
}

function FloatingNav() {
  return (
    <div className="backdrop-blur-[14px] backdrop-filter bg-[rgba(255,255,255,0.6)] box-border content-stretch flex items-start max-w-[396px] overflow-clip pl-[6px] pr-[14px] py-[5px] relative rounded-[79px] shadow-[0px_7px_16.2px_0px_rgba(0,0,0,0.25)] shrink-0" data-name="Floating nav">
      <FloatingNavBarItem />
      <FloatingNavBarItem1 />
      <FloatingNavBarItem2 />
      <FloatingNavBarItem3 />
      <FloatingNavBarItem4 />
    </div>
  );
}

function FloatingNav1() {
  return (
    <div className="absolute content-stretch flex flex-col gap-[10px] items-center justify-center left-[22px] top-[875px]" data-name="Floating Nav">
      <div className="absolute bg-gradient-to-b from-[rgba(241,241,243,0)] h-[134px] left-[calc(50%-0.5px)] to-[#f1f1f3] top-1/2 translate-x-[-50%] translate-y-[-50%] w-[533px]" />
      <FloatingNav />
    </div>
  );
}

// Datos de muestra para el gráfico
const chartData = [
  { day: "Lun", calorias: 320 },
  { day: "Mar", calorias: 450 },
  { day: "Mié", calorias: 380 },
  { day: "Jue", calorias: 520 },
  { day: "Vie", calorias: 410 },
  { day: "Sáb", calorias: 600 },
  { day: "Dom", calorias: 350 },
];

function UserProfileCard() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.6)] box-border rounded-[28px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[20px] mb-[16px]">
      <div className="flex items-center gap-[16px]">
        <div className="size-[70px] rounded-full bg-gradient-to-br from-[#4c6ef5] to-[#845ef7] flex items-center justify-center shadow-lg">
          <span className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-white text-[28px]">AZ</span>
        </div>
        <div className="flex-1">
          <h2 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[18px] text-black mb-[4px]">Alex Zuñiga</h2>
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[14px] text-[rgba(0,0,0,0.6)]">Nivel Intermedio • 3 meses activo</p>
        </div>
        <div className="flex flex-col items-center">
          <div className="bg-[#4c6ef5] rounded-full px-[12px] py-[6px]">
            <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-white text-[14px]">Racha: 5 días</p>
          </div>
        </div>
      </div>
    </div>
  );
}

function StatCard({ icon, title, value, subtitle, color }: { icon: React.ReactNode; title: string; value: string; subtitle: string; color: string }) {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.6)] box-border rounded-[20px] shadow-[0px_4px_16px_0px_rgba(0,0,0,0.08)] p-[16px] flex flex-col">
      <div className="flex items-center gap-[10px] mb-[8px]">
        <div className={`size-[36px] rounded-full ${color} flex items-center justify-center`}>
          {icon}
        </div>
        <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-[rgba(0,0,0,0.7)]">{title}</p>
      </div>
      <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[24px] text-black mb-[2px]">{value}</p>
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[12px] text-[rgba(0,0,0,0.5)]">{subtitle}</p>
    </div>
  );
}

function StatsGrid() {
  return (
    <div className="grid grid-cols-2 gap-[12px] mb-[16px]">
      <StatCard
        icon={<Flame className="size-[20px] text-white" />}
        title="Calorías"
        value="3,280"
        subtitle="+15% esta semana"
        color="bg-gradient-to-br from-[#ff6b6b] to-[#ee5a6f]"
      />
      <StatCard
        icon={<Activity className="size-[20px] text-white" />}
        title="Entrenamientos"
        value="24"
        subtitle="Este mes"
        color="bg-gradient-to-br from-[#4c6ef5] to-[#5f3dc4]"
      />
      <StatCard
        icon={<Clock className="size-[20px] text-white" />}
        title="Tiempo Total"
        value="18.5h"
        subtitle="Este mes"
        color="bg-gradient-to-br from-[#20c997] to-[#12b886]"
      />
      <StatCard
        icon={<TrendingUp className="size-[20px] text-white" />}
        title="Progreso"
        value="85%"
        subtitle="Meta mensual"
        color="bg-gradient-to-br from-[#ffd43b] to-[#fab005]"
      />
    </div>
  );
}

function ProgressChart() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.6)] box-border rounded-[28px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[20px] mb-[16px]">
      <div className="flex items-center justify-between mb-[16px]">
        <div>
          <h3 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[16px] text-black mb-[2px]">Calorías Quemadas</h3>
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[13px] text-[rgba(0,0,0,0.6)]">Últimos 7 días</p>
        </div>
        <div className="flex items-center gap-[6px] bg-[rgba(76,110,245,0.1)] rounded-[12px] px-[10px] py-[6px]">
          <Calendar className="size-[16px] text-[#4c6ef5]" />
          <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[12px] text-[#4c6ef5]">Semana</span>
        </div>
      </div>
      <ResponsiveContainer width="100%" height={200}>
        <AreaChart data={chartData}>
          <defs>
            <linearGradient id="colorCalorias" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#4c6ef5" stopOpacity={0.3} />
              <stop offset="95%" stopColor="#4c6ef5" stopOpacity={0} />
            </linearGradient>
          </defs>
          <CartesianGrid strokeDasharray="3 3" stroke="rgba(0,0,0,0.05)" />
          <XAxis 
            dataKey="day" 
            stroke="rgba(0,0,0,0.4)" 
            style={{ fontSize: '12px', fontFamily: "'One_UI_Sans_APP_VF:400_Regular',sans-serif" }}
          />
          <YAxis 
            stroke="rgba(0,0,0,0.4)" 
            style={{ fontSize: '12px', fontFamily: "'One_UI_Sans_APP_VF:400_Regular',sans-serif" }}
          />
          <Tooltip 
            contentStyle={{ 
              backgroundColor: 'rgba(255,255,255,0.95)', 
              border: 'none', 
              borderRadius: '12px', 
              boxShadow: '0px 4px 12px rgba(0,0,0,0.1)',
              fontFamily: "'One_UI_Sans_APP_VF:600_SemiBold',sans-serif"
            }} 
          />
          <Area 
            type="monotone" 
            dataKey="calorias" 
            stroke="#4c6ef5" 
            strokeWidth={3}
            fillOpacity={1} 
            fill="url(#colorCalorias)" 
          />
        </AreaChart>
      </ResponsiveContainer>
    </div>
  );
}

function RecentActivity() {
  const activities = [
    { name: "Entrenamiento de Fuerza", time: "Hoy, 08:30 AM", duration: "45 min", calories: "380 kcal", icon: Dumbbell, color: "bg-[#4c6ef5]" },
    { name: "Cardio Intenso", time: "Ayer, 07:00 PM", duration: "30 min", calories: "420 kcal", icon: Activity, color: "bg-[#ff6b6b]" },
    { name: "Yoga y Estiramiento", time: "Hace 2 días", duration: "25 min", calories: "150 kcal", icon: Activity, color: "bg-[#20c997]" },
  ];

  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.6)] box-border rounded-[28px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[20px] mb-[120px]">
      <h3 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[16px] text-black mb-[16px]">Actividad Reciente</h3>
      <div className="space-y-[12px]">
        {activities.map((activity, index) => {
          const Icon = activity.icon;
          return (
            <div key={index} className="flex items-center gap-[14px] p-[12px] bg-[rgba(255,255,255,0.4)] rounded-[16px]">
              <div className={`size-[44px] rounded-full ${activity.color} flex items-center justify-center`}>
                <Icon className="size-[22px] text-white" />
              </div>
              <div className="flex-1">
                <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[14px] text-black">{activity.name}</p>
                <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[12px] text-[rgba(0,0,0,0.5)]">{activity.time}</p>
              </div>
              <div className="text-right">
                <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-black">{activity.duration}</p>
                <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[12px] text-[rgba(0,0,0,0.5)]">{activity.calories}</p>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

function ScrollContent() {
  return (
    <div className="absolute left-0 top-[78px] w-full px-[18px] pb-[20px] overflow-y-auto" style={{ height: 'calc(100% - 78px)' }}>
      <UserProfileCard />
      <StatsGrid />
      <ProgressChart />
      <RecentActivity />
    </div>
  );
}

export default function EstadisticasGraficosYProgreso() {
  return (
    <div className="bg-[#f1f1f3] overflow-clip relative rounded-[20px] size-full" data-name="Estadisticas (Graficos y Progreso)">
      <TopAppBar />
      <ScrollContent />
      <FloatingNav1 />
    </div>
  );
}