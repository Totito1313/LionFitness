import svgPaths from "./svg-kgic2pmq0g";
import { Plus, Clock, Target, ChevronRight, Star, Copy, Play, TrendingUp } from "lucide-react";
import { useState } from "react";

function Frame5() {
  return (
    <div className="basis-0 grow h-full min-h-px min-w-px relative shrink-0">
      <div className="flex flex-col justify-center size-full">
        <div className="box-border content-stretch flex flex-col items-start justify-center px-[8px] py-[4px] relative size-full">
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] font-bold leading-[normal] relative shrink-0 text-[20px] text-black w-full">Rutinas</p>
        </div>
      </div>
    </div>
  );
}

function Left() {
  return (
    <div className="basis-0 content-stretch flex gap-[10px] grow items-center min-h-px min-w-px relative shrink-0" data-name="Left">
      <div className="basis-0 flex flex-row grow items-center self-stretch shrink-0">
        <Frame5 />
      </div>
    </div>
  );
}

function Shape() {
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

function Frame6() {
  return (
    <div className="relative rounded-[40px] shrink-0 size-[50px]">
      <Shape />
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
      <Frame6 />
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

// Datos de rutinas
const routines = [
  {
    id: 1,
    name: "Piernas Destruidas",
    description: "Rutina completa de piernas con énfasis en volumen",
    exercises: 12,
    duration: "75-90 min",
    difficulty: "Avanzado",
    color: "from-[#4c6ef5] to-[#5f3dc4]",
    lastUsed: "Hace 2 días",
    isFavorite: true,
    muscleGroups: ["Cuádriceps", "Isquiotibiales", "Glúteos"]
  },
  {
    id: 2,
    name: "Pecho y Espalda",
    description: "Supersets para pecho y espalda, máximo pump",
    exercises: 10,
    duration: "60-75 min",
    difficulty: "Intermedio",
    color: "from-[#ff6b6b] to-[#ee5a6f]",
    lastUsed: "Hace 5 días",
    isFavorite: true,
    muscleGroups: ["Pecho", "Espalda", "Core"]
  },
  {
    id: 3,
    name: "Hombros y Brazos",
    description: "Desarrollo completo de hombros y brazos",
    exercises: 14,
    duration: "70-85 min",
    difficulty: "Intermedio",
    color: "from-[#20c997] to-[#12b886]",
    lastUsed: "Hace 7 días",
    isFavorite: false,
    muscleGroups: ["Hombros", "Bíceps", "Tríceps"]
  },
  {
    id: 4,
    name: "Full Body",
    description: "Entrenamiento de cuerpo completo para principiantes",
    exercises: 8,
    duration: "45-60 min",
    difficulty: "Principiante",
    color: "from-[#ffd43b] to-[#fab005]",
    lastUsed: "Nunca usado",
    isFavorite: false,
    muscleGroups: ["Todo el cuerpo"]
  },
  {
    id: 5,
    name: "Cardio HIIT",
    description: "Alta intensidad para quemar calorías rápido",
    exercises: 6,
    duration: "30-40 min",
    difficulty: "Avanzado",
    color: "from-[#ff6b6b] to-[#fa5252]",
    lastUsed: "Hace 10 días",
    isFavorite: false,
    muscleGroups: ["Cardio", "Resistencia"]
  }
];

function TabButton({ active, children, onClick }: { active: boolean; children: React.ReactNode; onClick: () => void }) {
  return (
    <button
      onClick={onClick}
      className={`font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[14px] px-[20px] py-[10px] rounded-[20px] transition-all ${
        active 
          ? "bg-[rgba(76,110,245,0.15)] text-[#4c6ef5]" 
          : "text-[rgba(0,0,0,0.5)] hover:bg-[rgba(0,0,0,0.03)]"
      }`}
    >
      {children}
    </button>
  );
}

function DifficultyBadge({ level }: { level: string }) {
  const colors = {
    "Principiante": "bg-[#12b886] text-white",
    "Intermedio": "bg-[#fab005] text-white",
    "Avanzado": "bg-[#ff6b6b] text-white"
  };
  
  return (
    <span className={`font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[11px] px-[10px] py-[4px] rounded-full ${colors[level as keyof typeof colors]}`}>
      {level}
    </span>
  );
}

function RoutineCard({ routine }: { routine: typeof routines[0] }) {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] rounded-[24px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[20px] mb-[16px]">
      {/* Header con gradiente */}
      <div className={`bg-gradient-to-br ${routine.color} rounded-[18px] p-[16px] mb-[16px] relative overflow-hidden`}>
        <div className="absolute top-2 right-2">
          <button className="size-[32px] rounded-full bg-[rgba(255,255,255,0.2)] backdrop-blur-sm flex items-center justify-center hover:bg-[rgba(255,255,255,0.3)] transition-colors">
            <Star className={`size-[18px] ${routine.isFavorite ? "fill-white" : ""} text-white`} />
          </button>
        </div>
        <h3 className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[22px] text-white mb-[6px]">{routine.name}</h3>
        <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[13px] text-[rgba(255,255,255,0.9)] mb-[12px]">{routine.description}</p>
        <div className="flex flex-wrap gap-[6px]">
          {routine.muscleGroups.map((muscle, idx) => (
            <span 
              key={idx} 
              className="font-['One_UI_Sans_APP_VF:500_Medium',sans-serif] text-[11px] bg-[rgba(255,255,255,0.25)] text-white px-[10px] py-[4px] rounded-full backdrop-blur-sm"
            >
              {muscle}
            </span>
          ))}
        </div>
      </div>

      {/* Info stats */}
      <div className="grid grid-cols-3 gap-[10px] mb-[16px]">
        <div className="bg-[rgba(0,0,0,0.03)] rounded-[14px] p-[12px] flex flex-col items-center">
          <Target className="size-[20px] text-[#4c6ef5] mb-[6px]" />
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[16px] text-black">{routine.exercises}</p>
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[11px] text-[rgba(0,0,0,0.5)]">Ejercicios</p>
        </div>
        <div className="bg-[rgba(0,0,0,0.03)] rounded-[14px] p-[12px] flex flex-col items-center">
          <Clock className="size-[20px] text-[#20c997] mb-[6px]" />
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[16px] text-black">{routine.duration.split('-')[0]}</p>
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[11px] text-[rgba(0,0,0,0.5)]">Duración</p>
        </div>
        <div className="bg-[rgba(0,0,0,0.03)] rounded-[14px] p-[12px] flex flex-col items-center justify-center">
          <TrendingUp className="size-[20px] text-[#ff6b6b] mb-[6px]" />
          <DifficultyBadge level={routine.difficulty} />
        </div>
      </div>

      {/* Last used */}
      <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[12px] text-[rgba(0,0,0,0.5)] mb-[16px]">
        Último uso: {routine.lastUsed}
      </p>

      {/* Actions */}
      <div className="flex gap-[10px]">
        <button className="flex-1 bg-[#4c6ef5] rounded-[16px] py-[12px] flex items-center justify-center gap-[8px] hover:bg-[#4263eb] transition-colors">
          <Play className="size-[18px] text-white" />
          <span className="font-['One_UI_Sans_APP_VF:600_SemiBold',sans-serif] text-[14px] text-white">Iniciar</span>
        </button>
        <button className="bg-[rgba(0,0,0,0.05)] rounded-[16px] px-[16px] flex items-center justify-center hover:bg-[rgba(0,0,0,0.08)] transition-colors">
          <Copy className="size-[20px] text-[rgba(0,0,0,0.6)]" />
        </button>
        <button className="bg-[rgba(0,0,0,0.05)] rounded-[16px] px-[16px] flex items-center justify-center hover:bg-[rgba(0,0,0,0.08)] transition-colors">
          <ChevronRight className="size-[20px] text-[rgba(0,0,0,0.6)]" />
        </button>
      </div>
    </div>
  );
}

function QuickStats() {
  return (
    <div className="backdrop-blur-md backdrop-filter bg-[rgba(255,255,255,0.7)] rounded-[24px] shadow-[0px_4px_20px_0px_rgba(0,0,0,0.1)] p-[20px] mb-[20px] mx-[18px]">
      <div className="flex items-center justify-between">
        <div>
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[13px] text-[rgba(0,0,0,0.6)] mb-[4px]">Esta semana</p>
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[24px] text-black">5 rutinas</p>
        </div>
        <div className="text-right">
          <p className="font-['One_UI_Sans_APP_VF:400_Regular',sans-serif] text-[13px] text-[rgba(0,0,0,0.6)] mb-[4px]">Tiempo total</p>
          <p className="font-['One_UI_Sans_APP_VF:700_Bold',sans-serif] text-[24px] text-black">6.5 horas</p>
        </div>
      </div>
    </div>
  );
}

function ScrollContent() {
  const [activeTab, setActiveTab] = useState<"mis-rutinas" | "explorar">("mis-rutinas");

  return (
    <div className="absolute left-0 top-[78px] w-full overflow-y-auto" style={{ height: 'calc(100% - 78px)' }}>
      <QuickStats />
      
      {/* Tabs */}
      <div className="flex gap-[8px] px-[18px] mb-[20px]">
        <TabButton active={activeTab === "mis-rutinas"} onClick={() => setActiveTab("mis-rutinas")}>
          Mis Rutinas
        </TabButton>
        <TabButton active={activeTab === "explorar"} onClick={() => setActiveTab("explorar")}>
          Explorar
        </TabButton>
      </div>

      {/* Rutinas */}
      <div className="px-[18px] mb-[120px]">
        {routines.map((routine) => (
          <RoutineCard key={routine.id} routine={routine} />
        ))}
      </div>
    </div>
  );
}

function CreateRoutineButton() {
  return (
    <button className="absolute bottom-[120px] right-[18px] size-[60px] bg-gradient-to-br from-[#4c6ef5] to-[#5f3dc4] rounded-full shadow-[0px_8px_24px_0px_rgba(76,110,245,0.4)] flex items-center justify-center hover:scale-105 transition-transform z-10">
      <Plus className="size-[28px] text-white" />
    </button>
  );
}

export default function Rutinas() {
  return (
    <div className="bg-[#f1f1f3] overflow-clip relative rounded-[20px] size-full" data-name="Rutinas">
      <TopAppBar />
      <ScrollContent />
      <CreateRoutineButton />
      <FloatingNav1 />
    </div>
  );
}