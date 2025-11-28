import EstadisticasGraficosYProgreso from "./imports/EstadisticasGraficosYProgreso";
import Entrenamientos from "./imports/Entrenamientos";
import Rutinas from "./imports/Rutinas";
import MedidasCorporales from "./imports/MedidasCorporales";
import Ejercicios from "./imports/Ejercicios";

export default function App() {
  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 p-4">
      <div className="w-full max-w-[440px] h-[956px] shadow-2xl">
        <Ejercicios />
      </div>
    </div>
  );
}