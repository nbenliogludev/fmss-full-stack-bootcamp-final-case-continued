"use client";

import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { getAdPackage, createAdPackage } from "api/adPackage";
import React, { useState, Fragment } from "react";
import { Dialog, Transition } from "@headlessui/react";
import { useSession } from "next-auth/react";

const formatDate = (date: string) => {
  return new Date(date).toLocaleDateString();
};

const getStatusLabel = (status: string) => {
  switch (status) {
    case "ACTIVE":
      return "Active";
    case "PASSIVE":
      return "Passive";
    default:
      return status;
  }
};

const AdPackagesPage: React.FC = () => {
  const queryClient = useQueryClient();
  const { data: session } = useSession();
  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["adPackage"],
    queryFn: () => getAdPackage(session?.user.id!),
  });

  const mutation = useMutation({
    mutationFn: createAdPackage
  });

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const handleCreate = () => {
    mutation.mutate(session?.user.id!);
    closeModal();
  };

  if (isLoading) {
    return <p>Loading...</p>;
  }

  if (isError || !data?.data) {
    return <p>No ad package found for the user.</p>;
  }

  const adPackage = data.data;

  return (
    <div className="bg-gray-100 py-10">
      <div className="mx-auto max-w-2xl px-4 sm:px-6 lg:max-w-7xl lg:px-8">

        <div className="mt-6 mb-6 flex justify-between">
          <button
            onClick={openModal}
            className="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
          >
            Buy Ad Package
          </button>
        </div>

        <div className="bg-white shadow-lg rounded-lg overflow-hidden">
          <div className="p-4">
            <h3 className="text-lg font-semibold text-gray-900">Package ID: {adPackage.id}</h3>
            <p className="mt-2 text-xl font-bold text-green-600">Number of Ads: {adPackage.numberOfAds}</p>
            <p className="mt-2 text-sm text-gray-600">Validity Period: {adPackage.validityPeriod} days</p>
            <p className="mt-2 text-sm text-gray-600">Expiration Date: {formatDate(adPackage.expirationDate)}</p>
            <p className="mt-2 text-xs text-gray-500">Status: {getStatusLabel(adPackage.status)}</p>
          </div>
        </div>
      </div>

      <Transition appear show={isModalOpen} as={Fragment}>
        <Dialog as="div" className="relative z-10" onClose={closeModal}>
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0 scale-95"
            enterTo="opacity-100 scale-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100 scale-100"
            leaveTo="opacity-0 scale-95"
          >
            <div className="fixed inset-0 bg-black bg-opacity-25" />
          </Transition.Child>

          <div className="fixed inset-0 overflow-y-auto">
            <div className="flex min-h-full items-center justify-center p-4 text-center">
              <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 scale-95"
                enterTo="opacity-100 scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 scale-100"
                leaveTo="opacity-0 scale-95"
              >
                <Dialog.Panel className="w-full max-w-md transform overflow-hidden rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all">
                  <Dialog.Title as="h3" className="text-lg font-medium leading-6 text-gray-900">
                    Create Ad Package
                  </Dialog.Title>
                  <div className="mt-2">
                    <p className="mt-2 text-sm text-gray-500">
                      Please confirm that you want to create a new ad package.
                    </p>
                  </div>

                  <div className="mt-4">
                    <button
                      type="button"
                      className="inline-flex justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-sm font-medium text-white hover:bg-blue-700 focus:outline-none focus-visible:ring-2 focus-visible:ring-blue-500 focus-visible:ring-offset-2"
                      onClick={handleCreate}
                    >
                      Buy
                    </button>
                    <button
                      type="button"
                      className="ml-2 inline-flex justify-center rounded-md border border-transparent bg-gray-300 px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-400 focus:outline-none focus-visible:ring-2 focus-visible:ring-gray-500 focus-visible:ring-offset-2"
                      onClick={closeModal}
                    >
                      Cancel
                    </button>
                  </div>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition>
    </div>
  );
};

export default AdPackagesPage;
