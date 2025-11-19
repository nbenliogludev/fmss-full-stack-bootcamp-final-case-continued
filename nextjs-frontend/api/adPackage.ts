import axiosInstance from './';

interface IAdPackage {
  id: number;
  userId: number;
  numberOfAds: number;
  validityPeriod: number;
  expirationDate: string;
  status: string;
}

interface IAdPackageResponse {
  data: IAdPackage | null;
  message: string;
  responseDate: string;
  success: boolean;
}

export const getAdPackage = async (userId: number): Promise<IAdPackageResponse> => {
  const response = await axiosInstance.get(`/ad-packages/user/${userId}`);
  return response.data;
};

export const createAdPackage = async (userId: number): Promise<void> => {
  await axiosInstance.post('/ad-packages', { userId });
};
