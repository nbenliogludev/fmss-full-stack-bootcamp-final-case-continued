import axiosInstance from './';

interface IAd {
  id: number;
  title: string;
  description: string;
  amount: number;
  status: string;
}

interface IAdsResponse {
  data: IAd[];
  message: string;
  responseDate: string;
  success: boolean;
}

interface ICreateAdRequest {
  title: string;
  description: string;
  amount: number;
  userId: number;
}

interface ICreateAdResponse {
  data: IAd;
  message: string;
  responseDate: string;
  success: boolean;
}

export const getAds = async (): Promise<IAdsResponse> => {
  const response = await axiosInstance.get('/ads');
  return response.data;
};

export const getMyAds = async (userId: number): Promise<IAdsResponse> => {
  const response = await axiosInstance.get(`/ads/user/${userId}`);
  return response.data;
};

export const createAd = async (ad: ICreateAdRequest): Promise<ICreateAdResponse> => {
  const response = await axiosInstance.post('/ads', ad);
  return response.data;
};

export const deleteAd = async (adId: number): Promise<void> => {
  await axiosInstance.delete(`/ads/${adId}`);
};
