export interface ActividadesRequestDTO {
    nombre: string;
    descripcion: string;
    fec_inicio: Date;
    fec_final: Date;
    precio: string;
    materiales: string;
    tipoActividadId: number;
}
