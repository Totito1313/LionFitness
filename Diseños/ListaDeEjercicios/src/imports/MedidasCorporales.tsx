import svgPaths from "./svg-vem8ks5kca";
import { Ruler, Weight, TrendingUp, TrendingDown, Calendar, Plus, Edit2, ChevronRight } from "lucide-react";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from "recharts";
import { useState } from "react";

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
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] font-bold leading-[normal] relative shrink-0 text-[20px] text-black w-full">Medidas Corporales</p>
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
        <g id="more">
          <path d={svgPaths.p34a001c0} fill="var(--fill-0, black)" id="Vector" stroke="var(--stroke-0, black)" strokeWidth="0.333333" />
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

function Right() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(241,241,243,0.5)] box-border content-stretch flex items-center justify-end relative rounded-[25px] shadow-[0px_0px_65px_0px_rgba(0,0,0,0.25)] shrink-0" data-name="Right">
      <Frame7 />
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

function Shape2() {
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
          <Shape2 />
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

function Shape3() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="report">
          <path d={svgPaths.p2bb5ab80} id="Vector" stroke="var(--stroke-0, black)" strokeLinecap="round" strokeLinejoin="round" strokeOpacity="0.6" strokeWidth="1.5" />
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
          <Shape3 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel1() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame1 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-[rgba(0,0,0,0.6)] text-center w-[84px]">Estadisticas</p>
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

function Shape4() {
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
          <Shape4 />
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

function Shape5() {
  return (
    <div className="relative shrink-0 size-[20px]" data-name="Shape">
      <svg className="block size-full" fill="none" preserveAspectRatio="none" viewBox="0 0 20 20">
        <g id="height">
          <path clipRule="evenodd" d={svgPaths.p1c1e7c80} fill="var(--fill-0, black)" fillRule="evenodd" id="Vector" />
          <path d={svgPaths.p38635680} id="Vector_2" stroke="var(--stroke-0, black)" strokeLinecap="round" strokeWidth="1.5" />
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
          <Shape5 />
        </div>
      </div>
    </div>
  );
}

function IconWithLabel3() {
  return (
    <div className="content-stretch flex flex-col gap-[2px] items-center relative shrink-0 w-[54px]" data-name="Icon with label 8.5">
      <Frame3 />
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] font-normal leading-[normal] relative shrink-0 text-[12px] text-black text-center w-[84px]">Medidas</p>
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

function Shape6() {
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
          <Shape6 />
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
    <div className="absolute bottom-[21px] content-stretch flex flex-col gap-[10px] items-center justify-center left-1/2 translate-x-[-50%]" data-name="Floating Nav">
      <div className="absolute bg-gradient-to-b from-[rgba(241,241,243,0)] h-[134px] left-[calc(50%-0.5px)] to-[#f1f1f3] top-1/2 translate-x-[-50%] translate-y-[-50%] w-[533px]" />
      <FloatingNav />
    </div>
  );
}

// Datos de medidas corporales
const bodyMeasurements = [
  { id: 1, name: "Peso", value: "78.5", unit: "kg", change: -2.3, icon: Weight, color: "from-[#4c6ef5] to-[#5f3dc4]" },
  { id: 2, name: "Pecho", value: "102", unit: "cm", change: 3.5, icon: Ruler, color: "from-[#ff6b6b] to-[#ee5a6f]" },
  { id: 3, name: "Cintura", value: "82", unit: "cm", change: -4.2, icon: Ruler, color: "from-[#20c997] to-[#12b886]" },
  { id: 4, name: "Brazos", value: "38", unit: "cm", change: 2.1, icon: Ruler, color: "from-[#ffd43b] to-[#fab005]" },
  { id: 5, name: "Piernas", value: "58", unit: "cm", change: 3.8, icon: Ruler, color: "from-[#845ef7] to-[#7048e8]" },
  { id: 6, name: "Caderas", value: "95", unit: "cm", change: -1.5, icon: Ruler, color: "from-[#ff8787] to-[#ff6b6b]" },
];

// Datos para gráfico de peso
const weightData = [
  { date: "Ene", peso: 82.5 },
  { date: "Feb", peso: 81.2 },
  { date: "Mar", peso: 80.1 },
  { date: "Abr", peso: 79.5 },
  { date: "May", peso: 78.5 },
];

function CurrentMeasurementCard({ measurement }: { measurement: typeof bodyMeasurements[0] }) {
  const Icon = measurement.icon;
  const isPositive = measurement.change > 0;
  const TrendIcon = isPositive ? TrendingUp : TrendingDown;
  
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] rounded-[20px] shadow-[0px_4px_16px_0px_rgba(0,0,0,0.08)] p-[16px]">
      <div className="flex items-start justify-between mb-[10px]">
        <div className={`size-[40px] rounded-full bg-gradient-to-br ${measurement.color} flex items-center justify-center shadow-md`}>
          <Icon className="size-[20px] text-white" />
        </div>
        <div className={`flex items-center gap-[4px] px-[8px] py-[4px] rounded-full ${isPositive ? 'bg-[rgba(32,201,151,0.15)]' : 'bg-[rgba(255,107,107,0.15)]'}`}>
          <TrendIcon className={`size-[14px] ${isPositive ? 'text-[#12b886]' : 'text-[#ff6b6b]'}`} />
          <span className={`font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[11px] ${isPositive ? 'text-[#12b886]' : 'text-[#ff6b6b]'}`}>
            {Math.abs(measurement.change)}
          </span>
        </div>
      </div>
      <p className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[13px] text-[rgba(0,0,0,0.6)] mb-[4px]">{measurement.name}</p>
      <div className="flex items-baseline gap-[4px]">
        <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[28px] text-black">{measurement.value}</p>
        <p className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[16px] text-[rgba(0,0,0,0.5)]">{measurement.unit}</p>
      </div>
    </div>
  );
}

function MeasurementsGrid() {
  return (
    <div className="px-[18px] mb-[20px]">
      <div className="flex items-center justify-between mb-[14px]">
        <h2 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[18px] text-black">Medidas Actuales</h2>
        <button className="flex items-center gap-[6px] bg-[#4c6ef5] rounded-[16px] px-[14px] py-[8px] hover:bg-[#4263eb] transition-colors">
          <Plus className="size-[16px] text-white" />
          <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-white">Añadir</span>
        </button>
      </div>
      <div className="grid grid-cols-2 gap-[12px]">
        {bodyMeasurements.map((measurement) => (
          <CurrentMeasurementCard key={measurement.id} measurement={measurement} />
        ))}
      </div>
    </div>
  );
}

function WeightProgressChart() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] rounded-[24px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[20px] mx-[18px] mb-[20px]">
      <div className="flex items-center justify-between mb-[16px]">
        <div>
          <h3 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[16px] text-black mb-[2px]">Progreso de Peso</h3>
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[13px] text-[rgba(0,0,0,0.6)]">Últimos 5 meses</p>
        </div>
        <div className="flex items-center gap-[6px] bg-[rgba(255,107,107,0.15)] rounded-[12px] px-[10px] py-[6px]">
          <TrendingDown className="size-[16px] text-[#ff6b6b]" />
          <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[12px] text-[#ff6b6b]">-4 kg</span>
        </div>
      </div>
      <ResponsiveContainer width="100%" height={180}>
        <LineChart data={weightData}>
          <defs>
            <linearGradient id="colorPeso" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#4c6ef5" stopOpacity={0.2} />
              <stop offset="95%" stopColor="#4c6ef5" stopOpacity={0} />
            </linearGradient>
          </defs>
          <CartesianGrid strokeDasharray="3 3" stroke="rgba(0,0,0,0.05)" />
          <XAxis 
            dataKey="date" 
            stroke="rgba(0,0,0,0.4)" 
            style={{ fontSize: '12px', fontFamily: "'One_UI_Sans_APP_VF:400_Regular',sans-serif" }}
          />
          <YAxis 
            domain={[75, 85]}
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
          <Line 
            type="monotone" 
            dataKey="peso" 
            stroke="#4c6ef5" 
            strokeWidth={3}
            dot={{ fill: '#4c6ef5', strokeWidth: 2, r: 5 }}
            activeDot={{ r: 7 }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}

function MeasurementHistoryItem({ date, measurements }: { date: string; measurements: string }) {
  return (
    <div className="flex items-center gap-[14px] p-[16px] bg-[rgba(255,255,255,0.5)] rounded-[18px] mb-[10px]">
      <div className="size-[44px] rounded-full bg-gradient-to-br from-[#4c6ef5] to-[#5f3dc4] flex items-center justify-center">
        <Calendar className="size-[20px] text-white" />
      </div>
      <div className="flex-1">
        <p className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[14px] text-black mb-[2px]">{date}</p>
        <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[12px] text-[rgba(0,0,0,0.6)]">{measurements}</p>
      </div>
      <button className="size-[36px] rounded-full hover:bg-[rgba(0,0,0,0.05)] flex items-center justify-center transition-colors">
        <Edit2 className="size-[18px] text-[rgba(0,0,0,0.6)]" />
      </button>
    </div>
  );
}

function MeasurementHistory() {
  const history = [
    { date: "25 de Mayo, 2024", measurements: "6 medidas registradas" },
    { date: "18 de Mayo, 2024", measurements: "6 medidas registradas" },
    { date: "11 de Mayo, 2024", measurements: "6 medidas registradas" },
    { date: "4 de Mayo, 2024", measurements: "5 medidas registradas" },
  ];

  return (
    <div className="px-[18px] mb-[120px]">
      <div className="flex items-center justify-between mb-[14px]">
        <h2 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[18px] text-black">Historial</h2>
        <button className="flex items-center gap-[4px]">
          <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-[#4c6ef5]">Ver todo</span>
          <ChevronRight className="size-[16px] text-[#4c6ef5]" />
        </button>
      </div>
      <div>
        {history.map((item, idx) => (
          <MeasurementHistoryItem key={idx} date={item.date} measurements={item.measurements} />
        ))}
      </div>
    </div>
  );
}

function QuickAddButton() {
  return (
    <button className="absolute bottom-[120px] right-[18px] size-[60px] bg-gradient-to-br from-[#4c6ef5] to-[#5f3dc4] rounded-full shadow-[0px_8px_24px_0px_rgba(76,110,245,0.4)] flex items-center justify-center hover:scale-105 transition-transform z-10">
      <Plus className="size-[28px] text-white" />
    </button>
  );
}

function LastUpdate() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(76,110,245,0.1)] rounded-[18px] p-[14px] mx-[18px] mb-[20px] flex items-center justify-between">
      <div className="flex items-center gap-[12px]">
        <div className="size-[10px] rounded-full bg-[#4c6ef5] animate-pulse" />
        <p className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[13px] text-[rgba(0,0,0,0.7)]">Última actualización: hace 3 días</p>
      </div>
      <button className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[13px] text-[#4c6ef5]">Actualizar</button>
    </div>
  );
}

function ScrollContent() {
  return (
    <div className="absolute left-0 top-[78px] w-full overflow-y-auto" style={{ height: 'calc(100% - 78px)' }}>
      <LastUpdate />
      <MeasurementsGrid />
      <WeightProgressChart />
      <MeasurementHistory />
    </div>
  );
}

export default function MedidasCorporales() {
  return (
    <div className="bg-[#f1f1f3] overflow-clip relative rounded-[20px] size-full" data-name="Medidas Corporales">
      <TopAppBar />
      <ScrollContent />
      <QuickAddButton />
      <FloatingNav1 />
    </div>
  );
}